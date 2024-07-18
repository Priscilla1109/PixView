package com.demo.PixView.controller;

import com.demo.PixView.mapper.UserMapper;
import com.demo.PixView.model.Meta;
import com.demo.PixView.model.User;
import com.demo.PixView.model.UserPageResponse;
import com.demo.PixView.model.UserResponse;
import com.demo.PixView.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserControllerTest {
    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    @Test
    public void testCreateNewUser_Success() {
        User mockUser = new User();
        mockUser.setUserId(1L);
        mockUser.setName("testUser");

        when(userService.createNewUser(any(User.class))).thenReturn(mockUser);

        User userRequest = new User();
        userRequest.setName("testUser");

        ResponseEntity<UserResponse> responseEntity = userController.createNewUser(userRequest);

        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals(UserMapper.toResponse(mockUser), responseEntity.getBody());

        // Verifying that userService.createNewUser was called
        verify(userService, times(1)).createNewUser(any(User.class));
        verifyNoMoreInteractions(userService);
    }

    @Test
    public void testGetUsersById_Success() {
        User mockUser = new User();
        mockUser.setUserId(1L);
        mockUser.setName("testUser");

        when(userService.getUserById(anyLong())).thenReturn(Optional.of(mockUser));

        ResponseEntity<UserResponse> responseEntity = userController.getUsersById(1L);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(UserMapper.toResponse(mockUser), responseEntity.getBody());

        verify(userService, times(1)).getUserById(anyLong());
        verifyNoMoreInteractions(userService);
    }

    @Test
    public void testGetUsersById_NotFound() {
        when(userService.getUserById(anyLong())).thenReturn(Optional.empty());

        ResponseEntity<UserResponse> responseEntity = userController.getUsersById(1L);

        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());

        verify(userService, times(1)).getUserById(anyLong());
        verifyNoMoreInteractions(userService);
    }

    @Test
    public void testListAllUsers_Success() {
        User mockUser1 = new User();
        mockUser1.setUserId(1L);
        mockUser1.setName("testUser1");
        UserResponse response1 = UserMapper.toResponse(mockUser1);

        User mockUser2 = new User();
        mockUser2.setUserId(2L);
        mockUser2.setName("testUser2");
        UserResponse response2 = UserMapper.toResponse(mockUser2);

        List<UserResponse> mockUsers = Arrays.asList(response1, response2);
        UserPageResponse<User> mockUserPageResponse = new UserPageResponse<>(mockUsers, new Meta(1, 10, 2, 1));

        when(userService.listAllUsers(anyInt(), anyInt())).thenReturn(mockUserPageResponse);

        ResponseEntity<UserPageResponse> responseEntity = userController.ListAllUsers(0, 10);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(mockUserPageResponse, responseEntity.getBody());

        verify(userService, times(1)).listAllUsers(anyInt(), anyInt());
        verifyNoMoreInteractions(userService);
    }

    @Test
    public void testDeleteUser_Success() throws Throwable {
        doNothing().when(userService).deleteUserById(anyLong());

        ResponseEntity<String> responseEntity = userController.deleteUser(1L);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("User deleted successfully", responseEntity.getBody());

        verify(userService, times(1)).deleteUserById(anyLong());
        verifyNoMoreInteractions(userService);
    }
}
