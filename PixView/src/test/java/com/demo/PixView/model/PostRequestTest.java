package com.demo.PixView.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PostRequestTest {
    private PostRequest postRequest;

    @Test
    public void testGettersAmdSetters() {
        String userName = "userName";
        String content = "content";

        postRequest = new PostRequest();
        postRequest.setUserName(userName);
        postRequest.setContent(content);

        assertEquals(userName, postRequest.getUserName());
        assertEquals(content, postRequest.getContent());
    }
}
