package com.demo.PixView.model;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LikeTest {
    private Like like;

    @Test
    public void testGettersAndSetters(){
        Long likeId = 1L;
        Long postId = 1L;
        Long userId = 1L;
        LocalDateTime localDateTime = LocalDateTime.now();

        like = new Like();
        like.setLikeId(likeId);
        like.setPostId(postId);
        like.setUserId(userId);
        like.setLocalDateTime(localDateTime);

        assertEquals(likeId, like.getLikeId());
        assertEquals(postId, like.getPostId());
        assertEquals(userId, like.getUserId());
        assertEquals(localDateTime, like.getLocalDateTime());
    }

    @Test
    public void testAllArgsConstructor() {
        Long likeId = 1L;
        Long postId = 1L;
        Long userId = 1L;
        LocalDateTime localDateTime = LocalDateTime.now();
        like = new Like(likeId, postId, userId, localDateTime);

        assertEquals(likeId, like.getLikeId());
        assertEquals(postId, like.getPostId());
        assertEquals(userId, like.getUserId());
        assertEquals(localDateTime, like.getLocalDateTime());
    }
}
