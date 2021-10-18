package com.roadtripmaker.api;

import com.roadtripmaker.domain.Role;
import com.roadtripmaker.domain.Utilisateur;
import com.roadtripmaker.service.UtilisateurService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

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

}

@Data
class RoleToUserForm {
    private String mail;
    private String libelle;
}
