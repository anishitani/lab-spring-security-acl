package com.github.anishitani.domain.impl;

import com.github.anishitani.domain.PostService;
import com.github.anishitani.domain.model.Post;
import com.github.anishitani.domain.repository.PostRepository;

import java.util.UUID;

public class PostServiceImpl implements PostService {

    private PostRepository postRepository;

    public PostServiceImpl(PostRepository postRepository){
        this.postRepository = postRepository;
    }

    @Override
    public Post createPost(UUID writerId, Post post) {
        return postRepository.createPost(writerId, post);
    }

    @Override
    public Post fetchPost(Integer postId) {
        return postRepository.fetchPost(postId);
    }
}
