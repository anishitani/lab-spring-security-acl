package com.github.anishitani.infra.repository;

import com.github.anishitani.domain.model.Post;
import com.github.anishitani.domain.repository.PostRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.web.server.ResponseStatusException;

import javax.sql.DataSource;
import java.util.Map;
import java.util.UUID;

public class PostRepositoryImpl implements PostRepository {
    private final NamedParameterJdbcTemplate jdbc;
    private final Logger logger = LoggerFactory.getLogger(PostRepositoryImpl.class);

    public PostRepositoryImpl(DataSource ds) {
        jdbc = new NamedParameterJdbcTemplate(ds);
    }

    @Override
    public Post createPost(UUID writerId, Post post) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("title", post.getTitle());
        params.addValue("subject", post.getSubject());
        params.addValue("content", post.getContent());
        params.addValue("writerId", writerId);
        jdbc.update(
                " INSERT INTO posts(title, subject, content, writer_id) VALUES (:title, :subject, :content, :writerId) ",
                params, keyHolder
        );
        post.setId((Integer) keyHolder.getKeys().get("id"));
        return post;
    }

    @Override
    public Post fetchPost(Integer postId) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", postId);
        try {
            return jdbc.queryForObject(
                    " SELECT id, title, subject, content FROM posts WHERE id = :id ",
                    params, (rs, i) -> {
                        Post post = new Post();
                        post.setId(rs.getInt("id"));
                        post.setTitle(rs.getString("title"));
                        post.setSubject(rs.getString("subject"));
                        post.setContent(rs.getString("content"));
                        return post;
                    }
            );
        } catch (EmptyResultDataAccessException ex) {
            logger.error("Failed to fetch post", ex);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Post not found");
        } catch (Exception ex) {
            logger.error("Failed fetching post", ex);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Something went wrong :(");
        }
    }

    @Override
    public boolean isOwner(Integer id, UUID subjectId) {
        try {
            MapSqlParameterSource params = new MapSqlParameterSource();
            params.addValue("id", id);
            params.addValue("subjectId", subjectId);
            return jdbc.queryForObject(
                    " SELECT exists(SELECT FROM posts WHERE id = :id AND  writer_id = :subjectId ) ",
                    params, Boolean.class
            );
        } catch (Exception ex) {
            logger.error("Failed to process check", ex);
            return false;
        }
    }
}
