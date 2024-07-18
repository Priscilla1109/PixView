package com.demo.PixView.model;

import lombok.Data;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Data
public class PostResponseTest {
    private PostResponse postResponse;

    @Test
    public void testGettersAndSetters() {
        Long postId = 1L;
        Long userId = 1L;
        String userName = "userName";
        String content = "content";
        LocalDateTime localDateTime = LocalDateTime.now();
        int totalLikes = 0;
        int totalComments = 0;

        postResponse = new PostResponse();
        postResponse.setPostId(postId);
        postResponse.setUserId(userId);
        postResponse.setUserName(userName);
        postResponse.setContent(content);
        postResponse.setLocalDateTime(localDateTime);
        postResponse.setTotalLikes(totalLikes);
        postResponse.setTotalComments(totalComments);

        assertEquals(postId, postResponse.getPostId());
        assertEquals(userId, postResponse.getUserId());
        assertEquals(userName, postResponse.getUserName());
        assertEquals(content, postResponse.getContent());
        assertEquals(localDateTime, postResponse.getLocalDateTime());
        assertEquals(totalLikes, postResponse.getTotalLikes());
        assertEquals(totalComments, postResponse.getTotalComments());
    }
}
