package com.demo.PixView.model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PostResponse {
    private Long postId;
    private String userName;
    private String content;
    private LocalDateTime localDateTime;
    private int totalLikes;
    private int totalComments;
}
