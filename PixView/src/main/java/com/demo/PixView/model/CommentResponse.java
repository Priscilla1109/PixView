package com.demo.PixView.model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CommentResponse {
    private Long commentId;
    private Long postId;
    private String userName;
    private String content;
    private LocalDateTime localDateTime;
}