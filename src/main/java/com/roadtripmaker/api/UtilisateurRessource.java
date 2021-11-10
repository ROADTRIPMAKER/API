package com.roadtripmaker.api;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.roadtripmaker.domain.model.Role;
import com.roadtripmaker.domain.model.Utilisateur;
import com.roadtripmaker.service.UtilisateurService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URI;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.toList;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/")
@RequiredArgsConstructor
public class UtilisateurRessource {
    private final UtilisateurService utilisateurService;

    @GetMapping("/users")
    public ResponseEntity<List<Utilisateur>> getUsers() {
        return ResponseEntity.ok().body(utilisateurService.getUtilisateurs());
    }

    @PostMapping("/signup")
    public ResponseEntity<Utilisateur> signUp(@RequestBody Utilisateur utilisateur) {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/signup").toUriString());
        return ResponseEntity.created(uri).body(utilisateurService.signUp(utilisateur));
    }

    @PostMapping("/role")
    public ResponseEntity<Role> addRole(@RequestBody Role role) {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/role").toUriString());

        return ResponseEntity.created(uri).body(utilisateurService.addRole(role));
    }

    @PostMapping("/role/add")
    public ResponseEntity<?> assignRoleToAnUser(@RequestBody RoleToUserForm roleToUserForm) {
        utilisateurService.assignRoleToAnUser(roleToUserForm.getMail(), roleToUserForm.getLibelle());
        return ResponseEntity.ok().build();
    }

    @GetMapping("/token/refresh")
    public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String authorizationHeader = request.getHeader(AUTHORIZATION);
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            try {
                //TODO UTILITY CLASS POUR EVITER LE DRY
                String refreshToken = authorizationHeader.substring("Bearer ".length());
                //TODO Pas DRY, on peut éventuellement passer par une classe utils [prendre en compte le TODO d'avant]
                Algorithm algorithm = Algorithm.HMAC256("secret".getBytes());
                JWTVerifier verifier = JWT.require(algorithm).build();
                DecodedJWT decodedJWT = verifier.verify(refreshToken);
                String mail = decodedJWT.getSubject();
                Utilisateur utilisateur = utilisateurService.getUtilisateur(mail);

                String accessToken = JWT.create().withSubject(mail)
                        .withExpiresAt(new Date(System.currentTimeMillis() + 10 * 600 * 1000))
                        .withIssuer(request.getRequestURI().toString())
                        .withClaim("roles", utilisateur.getRoles()
                                .stream()
                                .map(Role::getLibelle)
                                .collect((toList())))
                        .sign(algorithm);

                Map<String, String> tokens = new HashMap<>();
                tokens.put("access_token", accessToken);
                tokens.put("refresh_token", refreshToken);
                response.setContentType(APPLICATION_JSON_VALUE);
                new ObjectMapper().writeValue(response.getOutputStream(), tokens);
            } catch (Exception exception) {
                response.setHeader("erreur", exception.getMessage());
                response.setStatus(FORBIDDEN.value());
                Map<String, String> error = new HashMap<>();
                error.put("error_message", exception.getMessage());
                response.setContentType(APPLICATION_JSON_VALUE);
                new ObjectMapper().writeValue(response.getOutputStream(), error);
            }
        } else {
            throw new RuntimeException("Le refresh token est manquant.");
        }

    }

}

@Data
class  RoleToUserForm {
    private String mail;
    private String libelle;
}
