package com.demo.PixView.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FriendsTest {
    private Friends friends;

    @Test
    public void testGettersAndSetters() {
        friends = new Friends();
        Long userId = 1L;
        Long friendId = 2L;

        friends.setUserId(userId);
        friends.setFriendId(friendId);

        assertEquals(userId, friends.getUserId());
        assertEquals(friendId, friends.getFriendId());
    }
}
