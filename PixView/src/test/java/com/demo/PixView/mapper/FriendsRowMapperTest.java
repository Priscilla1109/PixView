package com.demo.PixView.mapper;

import com.demo.PixView.model.Friends;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@Component
public class FriendsRowMapperTest {
    private FriendsRowMapper friendsRowMapper;
    private ResultSet resultSet;

    @BeforeEach
    public void setUp() {
        friendsRowMapper = new FriendsRowMapper();
        resultSet = mock(ResultSet.class);
    }

    @Test
    public void testMap() throws SQLException {
        when(resultSet.getLong("user_id")).thenReturn(1L);
        when(resultSet.getLong("friend_id")).thenReturn(2L);

        Friends friends = friendsRowMapper.map(resultSet, null);

        assertEquals(1L, friends.getUserId());
        assertEquals(2L, friends.getFriendId());

        verify(resultSet, times(1)).getLong("user_id");
        verify(resultSet, times(1)).getLong("friend_id");
    }
}
