package com.demo.PixView.mapper;

import com.demo.PixView.model.Post;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class PostRowMapperTest {
    private PostRowMapper postRowMapper;

    private ResultSet resultSet;

    @BeforeEach
    public void setUp() {
        postRowMapper = new PostRowMapper();
        resultSet = mock(ResultSet.class);
    }

    @Test
    public void testMap() throws SQLException {
        LocalDateTime now = LocalDateTime.now();

        when(resultSet.getLong("post_id")).thenReturn(1L);
        when(resultSet.getLong("user_id")).thenReturn(2L);
        when(resultSet.getString("user_name")).thenReturn("testUser");
        when(resultSet.getString("content")).thenReturn("Test content");
        when(resultSet.getTimestamp("local_date_time")).thenReturn(Timestamp.valueOf(now));
        when(resultSet.getInt("total_likes")).thenReturn(10);

        Post post = postRowMapper.map(resultSet, null);

        assertEquals(1L, post.getPostId());
        assertEquals(2L, post.getUserId());
        assertEquals("testUser", post.getUserName());
        assertEquals("Test content", post.getContent());
        assertEquals(now, post.getLocalDateTime());
        assertEquals(10, post.getTotalLikes());

        verify(resultSet, times(1)).getLong("post_id");
        verify(resultSet, times(1)).getLong("user_id");
        verify(resultSet, times(1)).getString("user_name");
        verify(resultSet, times(1)).getString("content");
        verify(resultSet, times(1)).getTimestamp("local_date_time");
        verify(resultSet, times(1)).getInt("total_likes");
    }
}
