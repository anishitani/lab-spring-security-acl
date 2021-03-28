package com.github.anishitani.api;

import com.github.anishitani.api.dto.PostDTO;
import com.github.anishitani.domain.PostService;
import com.github.anishitani.domain.model.Post;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.UUID;

@RestController
public class PostApi {

    private PostService postService;

    public PostApi(PostService postService) {
        this.postService = postService;
    }

    @PreAuthorize("@resourceAccessEvaluator.isSubject(principal.attributes.get('sub'),#writerId)")
    @PostMapping("writers/{writerId}/posts")
    public ResponseEntity<Void> createPost(@PathVariable("writerId") UUID writerId,
                                           @RequestBody PostDTO post) {
        Post created = postService.createPost(writerId, post.toDomain());
        return ResponseEntity.created(getLocation(created.getId())).build();
    }

    @PreAuthorize("@resourceAccessEvaluator.ownsPosts(principal.attributes.get('sub'),#postId)")
    @GetMapping("/posts/{postId}")
    public ResponseEntity<PostDTO> fetchPost(@PathVariable("postId") Integer postId) {
        PostDTO fetched = new PostDTO();
        fetched.fromDomain(postService.fetchPost(postId));
        return ResponseEntity.ok(fetched);
    }

    private URI getLocation(Object id) {
        return ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(id).toUri();
    }
}
