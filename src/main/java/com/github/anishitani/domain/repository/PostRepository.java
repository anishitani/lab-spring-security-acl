package com.github.anishitani.domain.repository;

import com.github.anishitani.domain.model.Post;

import java.util.UUID;

public interface PostRepository {
    Post createPost(UUID writerId, Post post);
    Post fetchPost(Integer postId);
    boolean isOwner(Integer id, UUID subjectId);
}
