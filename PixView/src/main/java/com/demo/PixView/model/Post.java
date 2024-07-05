package com.demo.PixView.model;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class Post {
    private Long postId;
    private Long userId;
    private String userName;
    private String content;
    private LocalDateTime localDateTime;
    private int totalLikes;
    private int totalComments;
}
