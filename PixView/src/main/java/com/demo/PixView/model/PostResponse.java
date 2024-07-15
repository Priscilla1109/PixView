package com.demo.PixView.model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PostResponse {
    private Long postId;
    private String userName;
    private Long userId;
    private String content;
    private LocalDateTime localDateTime;
    private int totalLikes;
    private int totalComments;

    public static PostResponse toResponse(Post post) {
        PostResponse postResponse = new PostResponse();
        postResponse.setPostId(post.getPostId());
        postResponse.setUserId(post.getUserId());
        postResponse.setUserName(post.getUserName());
        postResponse.setContent(post.getContent());
        postResponse.setLocalDateTime(post.getLocalDateTime());
        postResponse.setTotalLikes(post.getTotalLikes());
        postResponse.setTotalComments(post.getTotalComments());
        return postResponse;
    }
}
