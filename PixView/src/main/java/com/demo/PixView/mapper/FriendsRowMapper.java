package com.demo.PixView.mapper;

import com.demo.PixView.model.Friends;
import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

//Classe usada para mapear os resultados da consulta para o objeto Post
@Component
public class FriendsRowMapper implements RowMapper<Friends> {

    @Override
    public Friends map(ResultSet rs, StatementContext ctx) throws SQLException {
        Friends friends = new Friends();
        friends.setUserId(rs.getLong("user_id"));
        friends.setFriendId(rs.getLong("friend_id"));

        return friends;
    }
}
