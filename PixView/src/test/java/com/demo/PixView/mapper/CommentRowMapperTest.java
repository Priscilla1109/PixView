package com.demo.PixView.mapper;

import com.demo.PixView.model.Comment;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CommentRowMapperTest {
    @InjectMocks
    private CommentRowMapper commentRowMapper;

    private ResultSet resultSet;

    @BeforeEach
    public void setUp() {
        resultSet = mock(ResultSet.class);
    }

    @Test
    public void testMap() throws SQLException {
        LocalDateTime now = LocalDateTime.now();

        when(resultSet.getLong("comment_id")).thenReturn(1L);
        when(resultSet.getLong("post_id")).thenReturn(2L);
        when(resultSet.getLong("user_id")).thenReturn(3L);
        when(resultSet.getString("user_name")).thenReturn("testUser");
        when(resultSet.getString("content")).thenReturn("This is a test comment");
        when(resultSet.getTimestamp("local_date_time")).thenReturn(Timestamp.valueOf(now));

        Comment comment = commentRowMapper.map(resultSet, null);

        assertEquals(1L, comment.getCommentId());
        assertEquals(2L, comment.getPostId());
        assertEquals(3L, comment.getUserId());
        assertEquals("testUser", comment.getUserName());
        assertEquals("This is a test comment", comment.getContent());
        assertEquals(now, comment.getLocalDateTime());

        verify(resultSet, times(1)).getLong("comment_id");
        verify(resultSet, times(1)).getLong("post_id");
        verify(resultSet, times(1)).getLong("user_id");
        verify(resultSet, times(1)).getString("user_name");
        verify(resultSet, times(1)).getString("content");
        verify(resultSet, times(1)).getTimestamp("local_date_time");
    }
}
