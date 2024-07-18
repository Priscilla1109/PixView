package com.demo.PixView.model;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PostTest {
    private Post post;

    @Test
    public void testGettersAndSetters() {
        Long postId = 1L;
        Long userId = 1L;
        String userName = "userName";
        String content = "content";
        LocalDateTime localDateTime = LocalDateTime.now();
        int totalLikes = 0;
        int totalComments = 0;

        post = new Post();
        post.setPostId(postId);
        post.setUserId(userId);
        post.setUserName(userName);
        post.setContent(content);
        post.setLocalDateTime(localDateTime);
        post.setTotalLikes(totalLikes);
        post.setTotalComments(totalComments);

        assertEquals(postId, post.getPostId());
        assertEquals(userId, post.getUserId());
        assertEquals(userName, post.getUserName());
        assertEquals(content, post.getContent());
        assertEquals(localDateTime, post.getLocalDateTime());
        assertEquals(totalLikes, post.getTotalLikes());
        assertEquals(totalComments, post.getTotalComments());
    }
}
