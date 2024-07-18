package com.demo.PixView.model;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserTest {
    private User user;

    @Test
    public void testGettersAndSetters() {
        Long userId = 1L;
        String name = "name";
        String userName = "userName";
        LocalDate birthDate = LocalDate.ofEpochDay(11-9-2000);
        String email = "email";
        String password = "password";
        List<User> friend = new ArrayList<>();

        user = new User();
        user.setUserId(userId);
        user.setName(name);
        user.setUserName(userName);
        user.setBirthDate(birthDate);
        user.setEmail(email);
        user.setPassword(password);
        user.setFriend(friend);

        assertEquals(userId, user.getUserId());
        assertEquals(name, user.getName());
        assertEquals(userName, user.getUserName());
        assertEquals(birthDate, user.getBirthDate());
        assertEquals(email, user.getEmail());
        assertEquals(password, user.getPassword());
        assertEquals(friend, user.getFriend());
    }

    @Test
    public void testAllArgsConstructor() {
        Long userId = 1L;
        String name = "name";
        String userName = "userName";
        LocalDate birthDate = LocalDate.ofEpochDay(11 - 9 - 2000);
        String email = "email";
        String password = "password";
        List<User> friend = new ArrayList<>();

        user = new User(userId, name, userName, birthDate, email, password, friend);

        assertEquals(userId, user.getUserId());
        assertEquals(name, user.getName());
        assertEquals(userName, user.getUserName());
        assertEquals(birthDate, user.getBirthDate());
        assertEquals(email, user.getEmail());
        assertEquals(password, user.getPassword());
        assertEquals(friend, user.getFriend());
    }
}
