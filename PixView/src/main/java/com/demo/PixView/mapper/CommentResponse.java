package com.demo.PixView.mapper;

import com.demo.PixView.model.Comment;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CommentResponse {
    private Long commentId;
    private Long userId;
    private String content;
    private LocalDateTime localDateTime;

    public static CommentResponse toResponse(Comment comment) {
        CommentResponse commentResponse = new CommentResponse();
        commentResponse.setCommentId(comment.getCommentId());
        commentResponse.setUserId(comment.getUserId());
        commentResponse.setContent(comment.getContent());
        commentResponse.setLocalDateTime(comment.getLocalDateTime());
        return commentResponse;
    }
}