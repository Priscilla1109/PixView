package com.demo.PixView.mapper;

import com.demo.PixView.model.User;
import com.demo.PixView.model.UserResponse;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserMapperTest {
    @Test
    public void testToResponse() {
        LocalDate birthDate = LocalDate.of(1990, 5, 15);
        User user = new User();
        user.setUserId(1L);
        user.setUserName("testUser");
        user.setName("John Doe");
        user.setBirthDate(birthDate);
        user.setEmail("john.doe@example.com");

        UserResponse response = UserMapper.toResponse(user);

        assertEquals(1L, response.getUserId());
        assertEquals("testUser", response.getName());
        assertEquals("John Doe", response.getUserName());
        assertEquals(34, response.getAge()); // Assuming current year is 2024
        assertEquals(birthDate, response.getBirthDate());
        assertEquals("john.doe@example.com", response.getEmail());
    }

    @Test
    public void testToResponseList() {
        LocalDate birthDate1 = LocalDate.of(1990, 5, 15);
        LocalDate birthDate2 = LocalDate.of(1985, 10, 25);
        User user1 = new User();
        user1.setUserId(1L);
        user1.setName("testUser1");
        user1.setUserName("John Doe");
        user1.setBirthDate(birthDate1);
        user1.setEmail("john.doe@example.com");

        User user2 = new User();
        user2.setUserId(2L);
        user2.setName("testUser2");
        user2.setUserName("Jane Smith");
        user2.setBirthDate(birthDate2);
        user2.setEmail("jane.smith@example.com");

        List<User> userList = Arrays.asList(user1, user2);
        List<UserResponse> responseList = UserMapper.toResponseList(userList);

        assertEquals(2, responseList.size());

        UserResponse response1 = responseList.get(0);
        assertEquals(1L, response1.getUserId());
        assertEquals("testUser1", response1.getUserName());
        assertEquals("John Doe", response1.getName());
        assertEquals(34, response1.getAge());
        assertEquals(birthDate1, response1.getBirthDate());
        assertEquals("john.doe@example.com", response1.getEmail());

        UserResponse response2 = responseList.get(1);
        assertEquals(2L, response2.getUserId());
        assertEquals("testUser2", response2.getUserName());
        assertEquals("Jane Smith", response2.getName());
        assertEquals(38, response2.getAge());
        assertEquals(birthDate2, response2.getBirthDate());
        assertEquals("jane.smith@example.com", response2.getEmail());
    }

    @Test
    public void testCalculateAge_NullBirthDate() {
        int age = UserMapper.calculateAge(null);
        assertEquals(0, age);
    }
}
