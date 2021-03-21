package com.github.anishitani.infra.configuration;

import com.github.anishitani.domain.PostService;
import com.github.anishitani.domain.impl.PostServiceImpl;
import com.github.anishitani.domain.repository.PostRepository;
import com.github.anishitani.infra.repository.PostRepositoryImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class BeanConfiguration {
    @Bean
    PostService postService(PostRepository postRepository){
        return new PostServiceImpl(postRepository);
    }

    @Bean
    PostRepository postRepository(DataSource ds) {
        return new PostRepositoryImpl(ds);
    }
}
