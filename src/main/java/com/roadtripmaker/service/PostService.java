package com.roadtripmaker.service;

import com.roadtripmaker.domain.model.Post;

import java.util.List;
import java.util.UUID;

public interface PostService {
    Post create(Post post);

    Post getPost(UUID uuid);

    List<Post> getPosts();

    Boolean deletePost(UUID uuid);
}
