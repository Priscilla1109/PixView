package com.demo.PixView.controller;

import com.demo.PixView.exception.UserNotFoundException;
import com.demo.PixView.model.Friends;
import com.demo.PixView.model.User;
import com.demo.PixView.service.FriendsService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class FriendsControllerTest {
    @Mock
    private FriendsService friendsService;

    @InjectMocks
    private FriendsController friendsController;

    @Test
    public void testAddNewFriend_Success() {
        doNothing().when(friendsService).addNewFriend(anyLong(), anyLong());

        Friends requestFriends = new Friends();
        requestFriends.setFriendId(1L);
        requestFriends.setUserId(2L);

        ResponseEntity<String> responseEntity = friendsController.addNewFriend(requestFriends);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("Friend added successfully", responseEntity.getBody());

        verify(friendsService, times(1)).addNewFriend(anyLong(), anyLong());
        verifyNoMoreInteractions(friendsService);
    }

    @Test
    public void testAddNewFriend_UserNotFoundException() {
        doThrow(new UserNotFoundException("User not found")).when(friendsService).addNewFriend(anyLong(), anyLong());

        Friends requestFriends = new Friends();
        requestFriends.setFriendId(1L);
        requestFriends.setUserId(2L);

        ResponseEntity<String> responseEntity = friendsController.addNewFriend(requestFriends);

        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
        assertEquals("User not found", responseEntity.getBody());

        verify(friendsService, times(1)).addNewFriend(anyLong(), anyLong());
        verifyNoMoreInteractions(friendsService);
    }

    @Test
    public void testAddNewFriend_InternalServerError() {
        doThrow(new RuntimeException("Internal server error")).when(friendsService).addNewFriend(anyLong(), anyLong());

        Friends requestFriends = new Friends();
        requestFriends.setFriendId(1L);
        requestFriends.setUserId(2L);

        ResponseEntity<String> responseEntity = friendsController.addNewFriend(requestFriends);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
        assertEquals("Internal server error", responseEntity.getBody());

        verify(friendsService, times(1)).addNewFriend(anyLong(), anyLong());
        verifyNoMoreInteractions(friendsService);
    }

    @Test
    public void testRemoveFriend_Success() {
        doNothing().when(friendsService).removeFriend(anyLong(), anyLong());

        Friends requestFriends = new Friends();
        requestFriends.setFriendId(1L);
        requestFriends.setUserId(2L);

        ResponseEntity<String> responseEntity = friendsController.removeFriend(requestFriends);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("Friend removed successfully", responseEntity.getBody());

        verify(friendsService, times(1)).removeFriend(anyLong(), anyLong());
        verifyNoMoreInteractions(friendsService);
    }

    @Test
    public void testRemoveFriend_UserNotFoundException() {
        doThrow(new UserNotFoundException("User not found")).when(friendsService).removeFriend(anyLong(), anyLong());

        Friends requestFriends = new Friends();
        requestFriends.setFriendId(1L);
        requestFriends.setUserId(2L);

        ResponseEntity<String> responseEntity = friendsController.removeFriend(requestFriends);

        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
        assertEquals("User not found", responseEntity.getBody());

        verify(friendsService, times(1)).removeFriend(anyLong(), anyLong());
        verifyNoMoreInteractions(friendsService);
    }

    @Test
    public void testRemoveFriend_InternalServerError() {
        doThrow(new RuntimeException("Internal server error")).when(friendsService).removeFriend(anyLong(), anyLong());

        Friends requestFriends = new Friends();
        requestFriends.setFriendId(1L);
        requestFriends.setUserId(2L);

        ResponseEntity<String> responseEntity = friendsController.removeFriend(requestFriends);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
        assertEquals("Internal server error", responseEntity.getBody());

        verify(friendsService, times(1)).removeFriend(anyLong(), anyLong());
        verifyNoMoreInteractions(friendsService);
    }

//    @Test
//    public void testGetFriends_Success() {
//        // Mocking the behavior of FriendsService for getFriends
//        List<User> mockFriends = Arrays.asList(
//                new User(1L, "Friend1"),
//                new User(2L, "Friend2")
//        );
//        when(friendsService.getFriends(anyLong())).thenReturn(mockFriends);
//
//        // Executing the controller method
//        ResponseEntity<?> responseEntity = friendsController.getFriends(1L);
//
//        // Verifying the response
//        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
//        assertEquals(mockFriends, responseEntity.getBody());
//
//        // Verifying that friendsService.getFriends was called
//        verify(friendsService, times(1)).getFriends(anyLong());
//        verifyNoMoreInteractions(friendsService);
//    }

    @Test
    public void testGetFriends_UserNotFoundException() {
        // Mocking the behavior of FriendsService to throw UserNotFoundException
        when(friendsService.getFriends(anyLong())).thenThrow(new UserNotFoundException("User not found"));

        // Executing the controller method
        ResponseEntity<?> responseEntity = friendsController.getFriends(1L);

        // Verifying the response
        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
        assertEquals("User not found", responseEntity.getBody());

        // Verifying that friendsService.getFriends was called
        verify(friendsService, times(1)).getFriends(anyLong());
        verifyNoMoreInteractions(friendsService);
    }

    @Test
    public void testGetFriends_InternalServerError() {
        // Mocking the behavior of FriendsService to throw an unexpected exception
        when(friendsService.getFriends(anyLong())).thenThrow(new RuntimeException("Internal server error"));

        // Executing the controller method
        ResponseEntity<?> responseEntity = friendsController.getFriends(1L);

        // Verifying the response
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
        assertEquals("An unexpected error occurred", responseEntity.getBody());

        // Verifying that friendsService.getFriends was called
        verify(friendsService, times(1)).getFriends(anyLong());
        verifyNoMoreInteractions(friendsService);
    }
}
