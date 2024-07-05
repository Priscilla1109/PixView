package com.demo.PixView.model;

import lombok.Data;

@Data
public class Like {
    private Long likeId;
    private Long postId;
    private Long userId;
}
