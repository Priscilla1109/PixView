package com.demo.PixView.mapper;

import com.demo.PixView.model.Comment;
import com.demo.PixView.model.User;
import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

//Classe usada para mapear os resultados da consulta para o objeto Post
@Component
public class CommentRowMapper implements RowMapper<Comment> {

    @Override
    public Comment map(ResultSet rs, StatementContext ctx) throws SQLException {
        Comment comment = new Comment();
        comment.setCommentId(rs.getLong("comment_id"));
        comment.setPostId(rs.getLong("post_id"));
        comment.setUserId(rs.getLong("user_id"));
        comment.setUserName(rs.getString("user_name"));
        comment.setContent(rs.getString("content"));
        comment.setLocalDateTime(rs.getTimestamp("local_date_time").toLocalDateTime());

        return comment;
    }
}
