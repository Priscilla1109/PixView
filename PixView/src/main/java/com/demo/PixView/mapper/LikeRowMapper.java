package com.demo.PixView.mapper;

import com.demo.PixView.model.Like;
import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

//Classe usada para mapear os resultados da consulta para o objeto Post
@Component
public class LikeRowMapper implements RowMapper<Like> {

    @Override
    public Like map(ResultSet rs, StatementContext ctx) throws SQLException {
        Like like = new Like();
        like.setPostId(rs.getLong("post_id"));
        like.setUserId(rs.getLong("user_id"));

        return like;
    }
}
