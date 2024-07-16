package com.demo.PixView.model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class LikeResponse {
    private Long likeId;
    private Long postId;
    private Long userId;
    private String userName;
    private LocalDateTime localDateTime;
}
