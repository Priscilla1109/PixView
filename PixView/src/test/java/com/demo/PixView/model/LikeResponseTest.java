package com.demo.PixView.model;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LikeResponseTest {
    private LikeResponse likeResponse;

    @Test
    public void testGettersAndSetters() {
        Long likeId = 1L;
        Long postId = 1L;
        Long userId = 1L;
        String userName = "userName";
        LocalDateTime localDateTime = LocalDateTime.now();

        likeResponse = new LikeResponse();
        likeResponse.setLikeId(likeId);
        likeResponse.setPostId(postId);
        likeResponse.setUserId(userId);
        likeResponse.setUserName(userName);
        likeResponse.setLocalDateTime(localDateTime);

        assertEquals(likeId, likeResponse.getLikeId());
        assertEquals(postId, likeResponse.getPostId());
        assertEquals(userId, likeResponse.getUserId());
        assertEquals(userName, likeResponse.getUserName());
        assertEquals(localDateTime, likeResponse.getLocalDateTime());
    }

    @Test
    public void testAllArgsConstructor() {
        Long likeId = 1L;
        Long postId = 1L;
        Long userId = 1L;
        String userName = "userName";
        LocalDateTime localDateTime = LocalDateTime.now();

        likeResponse = new LikeResponse(likeId, postId, userId, userName, localDateTime);

        assertEquals(likeId, likeResponse.getLikeId());
        assertEquals(postId, likeResponse.getPostId());
        assertEquals(userId, likeResponse.getUserId());
        assertEquals(userName, likeResponse.getUserName());
        assertEquals(localDateTime, likeResponse.getLocalDateTime());
    }
}
