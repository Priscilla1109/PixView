package com.demo.PixView.mapper;

import com.demo.PixView.model.Post;
import com.demo.PixView.model.PostResponse;

public class PostMapper {
    public static PostResponse toResponse(Post post) {
        PostResponse postResponse = new PostResponse();
        postResponse.setUserName(post.getUserName());
        postResponse.setUserId(post.getUserId());
        postResponse.setPostId(post.getPostId());
        postResponse.setContent(post.getContent());
        postResponse.setLocalDateTime(post.getLocalDateTime());
        postResponse.setTotalLikes(0);
        postResponse.setTotalComments(0);

        return postResponse;
    }
}
