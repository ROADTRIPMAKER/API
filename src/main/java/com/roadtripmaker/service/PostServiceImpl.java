package com.roadtripmaker.service;

import com.roadtripmaker.domain.model.Post;
import com.roadtripmaker.domain.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class PostServiceImpl implements PostService {
    private final PostRepository postRepository;

    @Override public Post create(Post post) {
        log.info("Sauvegarde d'un nouveau post  : {} en base de données.", post.getUuid());

        return this.postRepository.save(post);
    }

    @Override public Post getPost(UUID uuid) {
        log.info("Recherche du post : {}", uuid);

        return this.postRepository.findByUuid(uuid);
    }

    @Override public List<Post> getPosts() {
        log.info("Recherche de tous les posts");

        return this.postRepository.findAll();
    }

    @Override public Boolean deletePost(UUID uuid) {
        log.info("Suppression du post : {}", uuid);

        this.postRepository.deleteById(uuid);

        return true;
    }
}
