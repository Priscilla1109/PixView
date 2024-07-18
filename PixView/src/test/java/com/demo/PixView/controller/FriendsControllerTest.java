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

import java.util.ArrayList;
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

    @Test
    public void testGetFriends_Success() {
        List<User> mockFriends = new ArrayList<>();

        User user1 = new User();
        user1.setUserId(1L);
        user1.setUserName("Friend1");

        User user2 = new User();
        user2.setUserId(2L);
        user2.setUserName("Friend2");

        mockFriends.add(user1);
        mockFriends.add(user2);

        when(friendsService.listFriendsByUserId(anyLong())).thenReturn(mockFriends);

        ResponseEntity<?> responseEntity = friendsController.listFriendsByUserId(1L);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(mockFriends, responseEntity.getBody());

        verify(friendsService, times(1)).listFriendsByUserId(anyLong());
        verifyNoMoreInteractions(friendsService);
    }

    @Test
    public void testGetFriends_UserNotFoundException() {
        when(friendsService.listFriendsByUserId(anyLong())).thenThrow(new UserNotFoundException("User not found"));

        ResponseEntity<?> responseEntity = friendsController.listFriendsByUserId(1L);

        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
        assertEquals("User not found", responseEntity.getBody());

        verify(friendsService, times(1)).listFriendsByUserId(anyLong());
        verifyNoMoreInteractions(friendsService);
    }

    @Test
    public void testGetFriends_InternalServerError() {
        when(friendsService.listFriendsByUserId(anyLong())).thenThrow(new RuntimeException("Internal server error"));

        ResponseEntity<?> responseEntity = friendsController.listFriendsByUserId(1L);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
        assertEquals("An unexpected error occurred", responseEntity.getBody());

        verify(friendsService, times(1)).listFriendsByUserId(anyLong());
        verifyNoMoreInteractions(friendsService);
    }
}
