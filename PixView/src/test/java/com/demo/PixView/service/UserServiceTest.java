package com.demo.PixView.service;

import com.demo.PixView.exception.InvalidAgeException;
import com.demo.PixView.model.User;
import com.demo.PixView.model.UserPageResponse;
import com.demo.PixView.repository.PostRepository;
import com.demo.PixView.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
    @Mock
    private UserRepository userRepository;

    @Mock
    private PostRepository postRepository;

    @Mock
    private FriendsService friendsService;

    @InjectMocks
    private UserService userService;

    @Test
    void testCreateNewUser_Successful() {
        User user = new User();
        user.setUserName("user1");
        user.setBirthDate(LocalDate.of(2000, 1, 1));

        when(userRepository.createNewUser(any(User.class))).thenReturn(1L);

        User createdUser = userService.createNewUser(user);

        assertEquals(1L, createdUser.getUserId());
        verify(userRepository, times(1)).createNewUser(any(User.class));
    }

    @Test
    void testCreateNewUser_InvalidAge() {
        User user = new User();
        user.setUserName("user1");
        user.setBirthDate(LocalDate.now().minus(Period.ofYears(17)));

        InvalidAgeException thrown = assertThrows(
                InvalidAgeException.class,
                () -> userService.createNewUser(user),
                "Expected createNewUser to throw, but it didn't"
        );

        assertTrue(thrown.getMessage().contains("User must be at last 18 years old"));
        verify(userRepository, never()).createNewUser(any(User.class));
    }

    @Test
    void testGetUserById_UserExists() {
        Long userId = 1L;

        User user = new User();
        user.setUserId(userId);
        user.setUserName("user1");
        when(userRepository.selectByUserId(userId)).thenReturn(Optional.of(user));

        Optional<User> retrievedUser = userService.getUserById(userId);

        assertEquals(user, retrievedUser.orElse(null));
        verify(userRepository, times(1)).selectByUserId(userId);
    }

    @Test
    void testGetUserById_UserNotExists() {
        Long userId = 1L;

        when(userRepository.selectByUserId(userId)).thenReturn(Optional.empty());

        Optional<User> retrievedUser = userService.getUserById(userId);

        assertEquals(Optional.empty(), retrievedUser);
        verify(userRepository, times(1)).selectByUserId(userId);
    }

    @Test
    void testDeleteUserById() {
        Long userId = 1L;

        userService.deleteUserById(userId);

        verify(friendsService, times(1)).deleteAllFriendsByUserId(userId);
        verify(postRepository, times(1)).deletePostsByUserId(userId);
        verify(userRepository, times(1)).deleteUser(userId);
    }

    @Test
    void testListAllUsers() {
        int page = 0;
        int pageSize = 10;
        int totalUsers = 20;

        List<User> users = new ArrayList<>();
        for (int i = 1; i <= totalUsers; i++) {
            User user = new User();
            user.setUserId((long) i);
            user.setUserName("user" + i);
            users.add(user);
        }
        when(userRepository.findAll(anyInt(), anyInt())).thenReturn(users);
        when(userRepository.countAll()).thenReturn((long) totalUsers);

        UserPageResponse<User> userPageResponse = userService.listAllUsers(page, pageSize);

        assertEquals(users, userPageResponse.getUsers());
        assertEquals(totalUsers, userPageResponse.getMeta().getTotalElements());
        assertEquals(2, userPageResponse.getMeta().getTotalPage());
        verify(userRepository, times(1)).findAll(page * pageSize, pageSize);
        verify(userRepository, times(1)).countAll();
    }
}
