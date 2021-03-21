package com.github.anishitani.api.dto;

import com.github.anishitani.domain.model.Post;

public class PostDTO {
    public Integer id;
    public String title;
    public String subject;
    public String content;

    public Post toDomain() {
        Post domain = new Post();
        domain.setId(this.id);
        domain.setTitle(this.title);
        domain.setSubject(this.subject);
        domain.setContent(this.content);
        return domain;
    }

    public void fromDomain(Post domain) {
        this.id = domain.getId();
        this.title = domain.getTitle();
        this.subject = domain.getSubject();
        this.content = domain.getContent();
    }
}
