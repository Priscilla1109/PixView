package com.demo.PixView.mapper;

import com.demo.PixView.model.Comment;
import com.demo.PixView.model.CommentResponse;

public class CommentMapper {
    public static CommentResponse toResponse(Comment comment) {
        CommentResponse commentResponse = new CommentResponse();
        commentResponse.setCommentId(comment.getCommentId());
        commentResponse.setUserName(comment.getUserName());
        commentResponse.setPostId(comment.getPostId());
        commentResponse.setContent(comment.getContent());
        commentResponse.setLocalDateTime(comment.getLocalDateTime());
        return commentResponse;
    }
}
