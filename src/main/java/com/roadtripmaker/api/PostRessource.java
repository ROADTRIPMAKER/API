package com.roadtripmaker.api;

import com.roadtripmaker.domain.model.Post;
import com.roadtripmaker.domain.model.Reponse;
import com.roadtripmaker.service.PostServiceImpl;
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
@RequestMapping("/post")
@RequiredArgsConstructor
public class PostRessource {
    private final PostServiceImpl postService;

    @PostMapping("/create")
    public ResponseEntity<Reponse> createPost(@RequestBody @Valid Post post) {
        return ResponseEntity.ok(
                Reponse.builder()
                        .timeStamp(now())
                        .data(Map.of("result", postService.create(post)))
                        .message("Post créé")
                        .status(CREATED)
                        .statusCode(CREATED.value())
                        .build()
        );
    }

    @GetMapping("/list")
    public ResponseEntity<Reponse> getPosts() {
        return ResponseEntity.ok(
                Reponse.builder()
                        .timeStamp(now())
                        .data(Map.of("results", postService.getPosts()))
                        .message("Posts récupérés")
                        .status(OK)
                        .statusCode(OK.value())
                        .build()
        );
    }

    @GetMapping("/list/{id}")
    public ResponseEntity<Reponse> getPost(@PathVariable("id") UUID uuid) {
        return ResponseEntity.ok(
                Reponse.builder()
                        .timeStamp(now())
                        .data(Map.of("result", postService.getPost(uuid)))
                        .message("Post récupéré")
                        .status(OK)
                        .statusCode(OK.value())
                        .build()
        );
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Reponse> deletePost(@PathVariable("id") UUID uuid) {
        return ResponseEntity.ok(
                Reponse.builder()
                        .timeStamp(now())
                        .data(Map.of("result", postService.deletePost(uuid)))
                        .message("Post supprimé")
                        .status(OK)
                        .statusCode(OK.value())
                        .build()
        );
    }
}
