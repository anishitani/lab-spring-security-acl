package com.github.anishitani.domain;

import java.util.UUID;

public interface ResourceAccessEvaluator {
    boolean isSubject(UUID subjectId, UUID writerId);
    boolean ownsPosts(UUID subjectId, Integer id);
}
