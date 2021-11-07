package com.roadtripmaker.api;

import com.roadtripmaker.domain.model.Poste;
import com.roadtripmaker.domain.model.Reponse;
import com.roadtripmaker.service.PosteServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Map;

import static java.time.LocalDateTime.now;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/")
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

    @PostMapping("/postes")
    public ResponseEntity<Reponse> getPostes() {
        return ResponseEntity.ok(
                Reponse.builder()
                        .timeStamp(now())
                        .data(Map.of("postes", posteService.getPostes()))
                        .message("Poste récupérés")
                        .status(OK)
                        .statusCode(OK.value())
                        .build()
        );
    }
}
