package com.demo.PixView.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Like {
    private Long likeId;
    private Long postId;
    private Long userId;
    private LocalDateTime localDateTime;
}
