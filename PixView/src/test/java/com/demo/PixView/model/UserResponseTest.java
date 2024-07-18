package com.demo.PixView.model;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserResponseTest {
    private UserResponse userResponse;

    @Test
    public void testGettersAndSetters() {
        Long userId = 1L;
        String name = "name";
        String userName = "userName";
        int age = 23;
        LocalDate birthDate = LocalDate.ofEpochDay(11-9-2000);
        String email = "email";

        userResponse = new UserResponse(userId, name, userName, age, birthDate, email);

        assertEquals(userId, userResponse.getUserId());
        assertEquals(name, userResponse.getName());
        assertEquals(userName, userResponse.getUserName());
        assertEquals(age, userResponse.getAge());
        assertEquals(birthDate, userResponse.getBirthDate());
        assertEquals(email, userResponse.getEmail());
    }
}
