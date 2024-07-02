package com.demo.PixView.mapper;

import com.demo.PixView.model.Post;
import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

//Classe usada para mapear os resultados da consulta para o objeto Post
@Component
public class PostRowMapper implements RowMapper<Post> {
    @Override
    public Post map(ResultSet rs, StatementContext ctx) throws SQLException {
        Post post = new Post();
        post.setPostId(rs.getLong("post_id"));
        post.setUserId(rs.getLong("user_id"));
        post.setUserName(rs.getString("user_name"));
        post.setContent(rs.getString("content"));
        post.setLocalDateTime(rs.getTimestamp("local_date_time").toLocalDateTime());

        return post;
    }
}
