package com.roadtripmaker.api;

import com.roadtripmaker.domain.model.Poste;
import com.roadtripmaker.domain.model.Reponse;
import com.roadtripmaker.service.PosteServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;
import java.util.UUID;

import static java.time.LocalDateTime.now;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/poste")
@RequiredArgsConstructor
public class PosteRessource {
    private final PosteServiceImpl posteService;

    @PostMapping("/create")
    public ResponseEntity<Reponse> createPoste(@RequestBody @Valid Poste poste) {
        return ResponseEntity.ok(
                Reponse.builder()
                        .timeStamp(now())
                        .data(Map.of("poste", posteService.create(poste)))
                        .message("Poste créé")
                        .status(CREATED)
                        .statusCode(CREATED.value())
                        .build()
        );
    }

    @GetMapping("/list")
    public ResponseEntity<Reponse> getPostes() {
        return ResponseEntity.ok(
                Reponse.builder()
                        .timeStamp(now())
                        .data(Map.of("postes", posteService.getPostes()))
                        .message("Postes récupérés")
                        .status(OK)
                        .statusCode(OK.value())
                        .build()
        );
    }

    @GetMapping("/list/{id}")
    public ResponseEntity<Reponse> getPoste(@PathVariable("id") UUID uuid) {
        return ResponseEntity.ok(
                Reponse.builder()
                        .timeStamp(now())
                        .data(Map.of("postes", posteService.getPoste(uuid)))
                        .message("Poste récupéré")
                        .status(OK)
                        .statusCode(OK.value())
                        .build()
        );
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Reponse> deletePoste(@PathVariable("id") UUID uuid) {
        return ResponseEntity.ok(
                Reponse.builder()
                        .timeStamp(now())
                        .data(Map.of("suppression", posteService.deletePoste(uuid)))
                        .message("Poste supprimé")
                        .status(OK)
                        .statusCode(OK.value())
                        .build()
        );
    }
}
