package com.roadtripmaker.api;

import com.roadtripmaker.domain.model.Response;
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
    public ResponseEntity<Response> getPhotos() {
        return ResponseEntity.ok(
                Response.builder()
                        .timeStamp(now())
                        .data(Map.of("results", photoService.getPhotos()))
                        .message("Photos récupérées")
                        .status(OK)
                        .statusCode(OK.value())
                        .build()
        );

    }

    @PostMapping("/create")
    public ResponseEntity<Response> savePhoto(@RequestBody @Valid MultipartFile file
    ) throws IOException {
        return ResponseEntity.ok(
                Response.builder()
                        .timeStamp(now())
                        .data(Map.of("result", photoService.savePhoto(file)))
                        .message("photo créée")
                        .status(CREATED)
                        .statusCode(CREATED.value())
                        .build()
        );
    }

    @GetMapping("/list/{name}")
    public ResponseEntity<Response> getPhoto(@PathVariable("name") String name) {
        return ResponseEntity.ok(
                Response.builder()
                        .timeStamp(now())
                        .data(Map.of("result", photoService.getPhoto(name)))
                        .message("Photo récupérée")
                        .status(OK)
                        .statusCode(OK.value())
                        .build()
        );
    }
}

