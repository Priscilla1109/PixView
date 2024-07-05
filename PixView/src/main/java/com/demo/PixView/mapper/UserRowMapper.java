package com.demo.PixView.mapper;

import com.demo.PixView.model.User;
import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

//Classe usada para mapear os resultados da consulta para o objeto Post
@Component
public class UserRowMapper implements RowMapper<User> {

    @Override
    public User map(ResultSet rs, StatementContext ctx) throws SQLException {
        User user = new User();
        user.setUserId(rs.getLong("user_id"));
        user.setUserName(rs.getString("user_name"));
        user.setName(rs.getString("name"));
        user.setBirthDate(rs.getDate("birth_date").toLocalDate());
        user.setEmail(rs.getString("email"));
        user.setPassword(rs.getString("password"));
        user.setFriend(new ArrayList<>());

        return user;
    }
}
