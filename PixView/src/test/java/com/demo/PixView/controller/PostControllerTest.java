package com.demo.PixView.controller;


import com.demo.PixView.mapper.PostMapper;
import com.demo.PixView.model.*;
import com.demo.PixView.service.PostService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PostControllerTest {
    @Mock
    private PostService postService;

    @InjectMocks
    private PostController postController;

    @Test
    public void testCreateNewPost_Success() {
        Post mockPost = new Post();
        mockPost.setPostId(1L);
        mockPost.setUserName("testUser");
        mockPost.setContent("This is a test post");

        when(postService.createNewPost(anyString(), anyString())).thenReturn(mockPost);

        PostRequest postRequest = new PostRequest();
        postRequest.setUserName("testUser");
        postRequest.setContent("This is a test post");

        ResponseEntity<PostResponse> responseEntity = postController.createNewPost(postRequest);

        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals(PostMapper.toResponse(mockPost), responseEntity.getBody());

        verify(postService, times(1)).createNewPost(anyString(), anyString());
        verifyNoMoreInteractions(postService);
    }

    @Test
    public void testGetPostsById_Success() {
        Post mockPost = new Post();
        mockPost.setPostId(1L);
        mockPost.setUserName("testUser");
        mockPost.setContent("This is a test post");

        when(postService.getPostsById(anyLong())).thenReturn(Optional.of(mockPost));

        ResponseEntity<PostResponse> responseEntity = postController.getPostsById(1L);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(PostMapper.toResponse(mockPost), responseEntity.getBody());

        verify(postService, times(1)).getPostsById(anyLong());
        verifyNoMoreInteractions(postService);
    }

    @Test
    public void testGetPostsById_NotFound() {
        when(postService.getPostsById(anyLong())).thenReturn(Optional.empty());

        ResponseEntity<PostResponse> responseEntity = postController.getPostsById(1L);

        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());

        verify(postService, times(1)).getPostsById(anyLong());
        verifyNoMoreInteractions(postService);
    }

    @Test
    public void testGetPostsByUserId_Success() {
        Post mockPost1 = new Post();
        mockPost1.setPostId(1L);
        mockPost1.setUserName("testUser");
        mockPost1.setContent("This is a test post");

        Post mockPost2 = new Post();
        mockPost2.setPostId(2L);
        mockPost2.setUserName("testUser");
        mockPost2.setContent("This is another test post");

        List<Post> mockPosts = Arrays.asList(mockPost1, mockPost2);

        when(postService.getPostsByUserId(anyLong())).thenReturn(mockPosts);

        ResponseEntity<List<PostResponse>> responseEntity = postController.getPostsByUserId(1L);

        List<PostResponse> expectedResponse = mockPosts.stream()
                .map(PostMapper::toResponse)
                .collect(Collectors.toList());
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(expectedResponse, responseEntity.getBody());

        verify(postService, times(1)).getPostsByUserId(anyLong());
        verifyNoMoreInteractions(postService);
    }

    @Test
    public void testListAllPosts_Success() {
        Post mockPost1 = new Post();
        mockPost1.setPostId(1L);
        mockPost1.setUserName("testUser");
        mockPost1.setContent("This is a test post");
        PostResponse response1 = PostMapper.toResponse(mockPost1);

        Post mockPost2 = new Post();
        mockPost2.setPostId(2L);
        mockPost2.setUserName("testUser");
        mockPost2.setContent("This is another test post");
        PostResponse response2 = PostMapper.toResponse(mockPost2);

        List<PostResponse> mockPosts = Arrays.asList(response1, response2);
        PostPageResponse<Post> mockPostPageResponse = new PostPageResponse<>(mockPosts, new Meta(1, 10, 2, 1));

        when(postService.listAllPosts(anyInt(), anyInt())).thenReturn(mockPostPageResponse);

        ResponseEntity<PostPageResponse> responseEntity = postController.listAllPosts(0, 10);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(mockPostPageResponse, responseEntity.getBody());

        verify(postService, times(1)).listAllPosts(anyInt(), anyInt());
        verifyNoMoreInteractions(postService);
    }

    @Test
    public void testDeletePost_Success() {
        doNothing().when(postService).deletePostById(anyLong());

        ResponseEntity<String> responseEntity = postController.deletePost(1L);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("Post deleted successfully", responseEntity.getBody());

        verify(postService, times(1)).deletePostById(anyLong());
        verifyNoMoreInteractions(postService);
    }
}
