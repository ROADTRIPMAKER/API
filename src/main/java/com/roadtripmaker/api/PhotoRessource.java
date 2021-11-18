package com.roadtripmaker.api;

import com.roadtripmaker.domain.model.Reponse;
import com.roadtripmaker.service.PhotoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.util.Map;
import java.util.UUID;

import static java.time.LocalDateTime.now;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/file")
@RequiredArgsConstructor
public class PhotoRessource {
    private final PhotoService photoService;

    @GetMapping("/list")
    public ResponseEntity<Reponse> getPhotos() {
        return ResponseEntity.ok(
                Reponse.builder()
                        .timeStamp(now())
                        .data(Map.of("posts", photoService.getPhotos()))
                        .message("Photos récupérées")
                        .status(OK)
                        .statusCode(OK.value())
                        .build()
        );

    }

    @PostMapping("/create")
    public ResponseEntity<Reponse> savePhoto(@RequestBody @Valid MultipartFile file
    ) throws IOException {
        return ResponseEntity.ok(
                Reponse.builder()
                        .timeStamp(now())
                        .data(Map.of("photo", photoService.savePhoto(file)))
                        .message("photo créée")
                        .status(CREATED)
                        .statusCode(CREATED.value())
                        .build()
        );
    }

    @GetMapping("/list/{id}")
    public ResponseEntity<Reponse> getPhoto(@PathVariable("id") UUID uuid) {
        return ResponseEntity.ok(
                Reponse.builder()
                        .timeStamp(now())
                        .data(Map.of("post", photoService.getPhoto(uuid)))
                        .message("Photo récupérée")
                        .status(OK)
                        .statusCode(OK.value())
                        .build()
        );
    }
}

