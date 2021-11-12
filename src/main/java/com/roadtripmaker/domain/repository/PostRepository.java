package com.roadtripmaker.domain.repository;

import com.roadtripmaker.domain.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PostRepository extends JpaRepository<Post, UUID> {
    Post findByUuid(UUID uuid);
}
