package com.demo.PixView.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CommentTest {
    private Comment comment;

    @BeforeEach
    public void setUp() {
        comment = new Comment();
    }

    @Test
    public void testGettersAndSetters() {
        Long commentId = 1L;
        Long postId = 2L;
        Long userId = 3L;
        String userName = "User";
        String content = "This is a comment.";
        LocalDateTime dateTime = LocalDateTime.now();

        comment.setCommentId(commentId);
        comment.setPostId(postId);
        comment.setUserId(userId);
        comment.setUserName(userName);
        comment.setContent(content);
        comment.setLocalDateTime(dateTime);

        assertEquals(commentId, comment.getCommentId());
        assertEquals(postId, comment.getPostId());
        assertEquals(userId, comment.getUserId());
        assertEquals(userName, comment.getUserName());
        assertEquals(content, comment.getContent());
        assertEquals(dateTime, comment.getLocalDateTime());
    }
}
