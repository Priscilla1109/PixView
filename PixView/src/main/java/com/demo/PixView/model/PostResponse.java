package com.demo.PixView.model;

import com.demo.PixView.mapper.CommentResponse;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class PostResponse {
    private Long postId;
    private String userName;
    private Long userId;
    private String content;
    private LocalDateTime localDateTime;
    private int totalLikes;
    private List<CommentResponse> totalComments;

    public static PostResponse toResponse(Post post) {
        PostResponse postResponse = new PostResponse();
        postResponse.setPostId(post.getPostId());
        postResponse.setUserId(post.getUserId());
        postResponse.setUserName(post.getUserName());
        postResponse.setContent(post.getContent());
        postResponse.setLocalDateTime(post.getLocalDateTime());
        postResponse.setTotalLikes(post.getTotalLikes());
        postResponse.setTotalComments(
                post.getTotalComments().stream()
                        .map(CommentResponse::toResponse)
                        .collect(Collectors.toList())
        );
        return postResponse;
    }
}
