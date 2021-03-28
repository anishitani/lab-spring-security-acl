package com.github.anishitani.domain.impl;

import com.github.anishitani.domain.repository.PostRepository;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.UUID;

public class ResourceAccessEvaluatorImpl implements com.github.anishitani.domain.ResourceAccessEvaluator {
    private PostRepository postRepository;

    public ResourceAccessEvaluatorImpl(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    public boolean isSubject(UUID subjectId, UUID writerId) {
        return writerId.equals(subjectId);
    }

    @Override
    public boolean ownsPosts(UUID subjectId, Integer id) {
        return postRepository.isOwner(id, subjectId);
    }
}
