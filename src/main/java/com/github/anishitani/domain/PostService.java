package com.github.anishitani.domain;

import com.github.anishitani.domain.model.Post;

import java.util.UUID;

public interface PostService {
    Post createPost(UUID writerId, Post post);
    Post fetchPost(Integer postId);
}