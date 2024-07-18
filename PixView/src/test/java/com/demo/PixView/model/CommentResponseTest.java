package com.demo.PixView.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CommentResponseTest {
    private CommentResponse commentResponse;

    @BeforeEach
    public void setUp() {
        commentResponse = new CommentResponse();
    }

    @Test
    public void testGettersAndSetters() {
        Long commentId = 1L;
        Long postId = 1L;
        String userName = "userName";
        String content = "content";
        LocalDateTime localDateTime = LocalDateTime.now();

        commentResponse.setCommentId(commentId);
        commentResponse.setPostId(postId);
        commentResponse.setUserName(userName);
        commentResponse.setContent(content);
        commentResponse.setLocalDateTime(localDateTime);

        assertEquals(commentId, commentResponse.getCommentId());
        assertEquals(postId, commentResponse.getPostId());
        assertEquals(userName, commentResponse.getUserName());
        assertEquals(content, commentResponse.getContent());
        assertEquals(localDateTime, commentResponse.getLocalDateTime());
    }
}