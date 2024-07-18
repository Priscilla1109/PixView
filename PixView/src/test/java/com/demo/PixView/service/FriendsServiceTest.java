package com.demo.PixView.service;

import com.demo.PixView.exception.UserNotFoundException;
import com.demo.PixView.model.User;
import com.demo.PixView.repository.JdbiFriendsRepository;
import com.demo.PixView.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class FriendsServiceTest {
    @Mock
    private UserRepository userRepositoryMock;

    @Mock
    private JdbiFriendsRepository jdbiFriendsRepositoryMock;

    @InjectMocks
    private FriendsService friendsService;

    @Test
    public void testAddNewFriend_Success() {
        Long userId = 1L;
        Long friendId = 2L;

        User mockUser = new User();
        mockUser.setUserId(userId);

        User mockFriend = new User();
        mockFriend.setUserId(friendId);

        when(userRepositoryMock.selectByUserId(userId)).thenReturn(Optional.of(mockUser));
        when(jdbiFriendsRepositoryMock.findById(friendId)).thenReturn(Optional.of(mockFriend));

        friendsService.addNewFriend(userId, friendId);

        verify(userRepositoryMock, times(1)).selectByUserId(userId);
        verify(jdbiFriendsRepositoryMock, times(1)).findById(friendId);
        verify(jdbiFriendsRepositoryMock, times(1)).addNewFriend(userId, friendId);
        verifyNoMoreInteractions(userRepositoryMock, jdbiFriendsRepositoryMock);
    }

    @Test
    public void testAddNewFriend_FriendNotFound() {
        Long userId = 1L;
        Long friendId = 2L;

        User mockUser = new User();
        mockUser.setUserId(userId);

        when(userRepositoryMock.selectByUserId(userId)).thenReturn(Optional.of(mockUser));
        when(jdbiFriendsRepositoryMock.findById(friendId)).thenReturn(Optional.empty());

        UserNotFoundException exception = assertThrows(UserNotFoundException.class, () -> {
            friendsService.addNewFriend(userId, friendId);
        });

        assertEquals("Friend not found with id: " + friendId, exception.getMessage());

        verify(userRepositoryMock, times(1)).selectByUserId(userId);
        verify(jdbiFriendsRepositoryMock, times(1)).findById(friendId);
        verifyNoMoreInteractions(userRepositoryMock, jdbiFriendsRepositoryMock);
    }

    @Test
    public void testRemoveFriend_Success() {
        Long userId = 1L;
        Long friendId = 2L;

        User mockUser = new User();
        mockUser.setUserId(userId);

        User mockFriend = new User();
        mockFriend.setUserId(friendId);

        when(userRepositoryMock.selectByUserId(userId)).thenReturn(Optional.of(mockUser));
        when(jdbiFriendsRepositoryMock.findById(friendId)).thenReturn(Optional.of(mockFriend));

        friendsService.removeFriend(userId, friendId);

        verify(userRepositoryMock, times(1)).selectByUserId(userId);
        verify(jdbiFriendsRepositoryMock, times(1)).findById(friendId);
        verify(jdbiFriendsRepositoryMock, times(1)).removeFriend(userId, friendId);
        verifyNoMoreInteractions(userRepositoryMock, jdbiFriendsRepositoryMock);
    }

    @Test
    public void testRemoveFriend_FriendNotFound() {
        Long userId = 1L;
        Long friendId = 2L;

        User mockUser = new User();
        mockUser.setUserId(userId);

        when(userRepositoryMock.selectByUserId(userId)).thenReturn(Optional.of(mockUser));
        when(jdbiFriendsRepositoryMock.findById(friendId)).thenReturn(Optional.empty());

        UserNotFoundException exception = assertThrows(UserNotFoundException.class, () -> {
            friendsService.removeFriend(userId, friendId);
        });

        assertEquals("Friend not found with id: " + friendId, exception.getMessage());

        verify(userRepositoryMock, times(1)).selectByUserId(userId);
        verify(jdbiFriendsRepositoryMock, times(1)).findById(friendId);
        verifyNoMoreInteractions(userRepositoryMock, jdbiFriendsRepositoryMock);
    }

    @Test
    public void testGetFriends() {
        Long userId = 1L;

        List<User> mockFriends = new ArrayList<>();
        mockFriends.add(new User(2L, "friend1", "Friend One", null, null, null, null));
        mockFriends.add(new User(3L, "friend2", "Friend Two", null, null, null, null));

        when(jdbiFriendsRepositoryMock.findFriends(userId)).thenReturn(mockFriends);

        List<User> retrievedFriends = friendsService.getFriends(userId);

        assertEquals(mockFriends.size(), retrievedFriends.size());
        assertEquals(mockFriends.get(0).getUserId(), retrievedFriends.get(0).getUserId());
        assertEquals(mockFriends.get(1).getUserId(), retrievedFriends.get(1).getUserId());

        verify(jdbiFriendsRepositoryMock, times(1)).findFriends(userId);
        verifyNoMoreInteractions(jdbiFriendsRepositoryMock);
    }

    @Test
    public void testDeleteAllFriendsByUserId() {
        Long userId = 1L;

        List<User> mockFriends = new ArrayList<>();
        mockFriends.add(new User(2L, "friend1", "Friend One", null, null, null, null));
        mockFriends.add(new User(3L, "friend2", "Friend Two", null, null, null, null));

        when(jdbiFriendsRepositoryMock.findFriends(userId)).thenReturn(mockFriends);

        friendsService.deleteAllFriendsByUserId(userId);

        verify(jdbiFriendsRepositoryMock, times(1)).findFriends(userId);
        verify(jdbiFriendsRepositoryMock, times(1)).removeFriend(userId, mockFriends.get(0).getUserId());
        verify(jdbiFriendsRepositoryMock, times(1)).removeFriend(userId, mockFriends.get(1).getUserId());
        verifyNoMoreInteractions(jdbiFriendsRepositoryMock);
    }
}
