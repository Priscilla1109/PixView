package com.demo.PixView.mapper;

import com.demo.PixView.model.Post;
import com.demo.PixView.model.PostResponse;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PostMapperTest {
    @Test
    public void testToResponse() {
        LocalDateTime now = LocalDateTime.now();

        Post post = new Post();
        post.setUserName("testUser");
        post.setUserId(1L);
        post.setPostId(2L);
        post.setContent("Test content");
        post.setLocalDateTime(now);
        post.setTotalLikes(10);
        post.setTotalComments(5);

        PostResponse postResponse = PostMapper.toResponse(post);

        assertEquals("testUser", postResponse.getUserName());
        assertEquals(1L, postResponse.getUserId());
        assertEquals(2L, postResponse.getPostId());
        assertEquals("Test content", postResponse.getContent());
        assertEquals(now, postResponse.getLocalDateTime());
        assertEquals(10, postResponse.getTotalLikes());
        assertEquals(5, postResponse.getTotalComments());
    }
}
