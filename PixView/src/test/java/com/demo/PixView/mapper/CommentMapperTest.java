package com.demo.PixView.mapper;

import com.demo.PixView.model.Comment;
import com.demo.PixView.model.CommentResponse;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CommentMapperTest {
    @Test
    public void testToResponse() {
        Comment comment = new Comment();
        comment.setCommentId(1L);
        comment.setUserName("testUser");
        comment.setPostId(2L);
        comment.setContent("This is a test comment");
        comment.setLocalDateTime(LocalDateTime.now());

        CommentResponse commentResponse = CommentMapper.toResponse(comment);

        assertEquals(comment.getCommentId(), commentResponse.getCommentId());
        assertEquals(comment.getUserName(), commentResponse.getUserName());
        assertEquals(comment.getPostId(), commentResponse.getPostId());
        assertEquals(comment.getContent(), commentResponse.getContent());
        assertEquals(comment.getLocalDateTime(), commentResponse.getLocalDateTime());
    }
}
