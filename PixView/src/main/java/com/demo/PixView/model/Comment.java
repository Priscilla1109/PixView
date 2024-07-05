package com.demo.PixView.model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Comment {
    private Long commentId;
    private Long postId;
    private Long userId;
    private String userName;
    private String content;
    private LocalDateTime localDateTime;
}
