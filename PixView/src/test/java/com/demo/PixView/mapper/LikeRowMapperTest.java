package com.demo.PixView.mapper;

import com.demo.PixView.model.Like;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class LikeRowMapperTest {
    private LikeRowMapper likeRowMapper;

    private ResultSet resultSet;

    @BeforeEach
    public void setUp() {
        likeRowMapper = new LikeRowMapper();
        resultSet = mock(ResultSet.class);
    }

    @Test
    public void testMap() throws SQLException {
        when(resultSet.getLong("post_id")).thenReturn(1L);
        when(resultSet.getLong("user_id")).thenReturn(2L);

        Like like = likeRowMapper.map(resultSet, null);

        assertEquals(1L, like.getPostId());
        assertEquals(2L, like.getUserId());

        verify(resultSet, times(1)).getLong("post_id");
        verify(resultSet, times(1)).getLong("user_id");
    }
}
