package com.demo.PixView.controller;


import com.demo.PixView.model.LikeResponse;
import com.demo.PixView.model.Post;
import com.demo.PixView.service.LikeService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class LikeControllerTest {
    @Mock
    private LikeService likeService;

    @InjectMocks
    private LikeController likeController;

    @Test
    public void testAddLike_Success() {
        doNothing().when(likeService).addLike(anyLong(), anyLong());

        Post requestPost = new Post();
        requestPost.setPostId(1L);
        requestPost.setUserId(2L);

        ResponseEntity<?> responseEntity = likeController.addLike(requestPost);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("Like added successfully", responseEntity.getBody());

        verify(likeService, times(1)).addLike(anyLong(), anyLong());
        verifyNoMoreInteractions(likeService);
    }

    @Test
    public void testDeleteLikeById_Success() {
        doNothing().when(likeService).deleteLikeById(anyLong());

        ResponseEntity<String> responseEntity = likeController.deleteLikeById(1L);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("Like deleted successfully", responseEntity.getBody());

        verify(likeService, times(1)).deleteLikeById(anyLong());
        verifyNoMoreInteractions(likeService);
    }

    @Test
    public void testGetLikesByPostId_Success() {
        // Creating mock LikeResponse objects without using constructors
        LikeResponse like1 = new LikeResponse();
        like1.setLikeId(1L);
        like1.setPostId(1L);
        like1.setUserId(2L);

        LikeResponse like2 = new LikeResponse();
        like2.setLikeId(2L);
        like2.setPostId(1L);
        like2.setUserId(3L);

        List<LikeResponse> mockLikes = Arrays.asList(like1, like2);
        when(likeService.getLikesByPostId(anyLong())).thenReturn(mockLikes);

        ResponseEntity<List<LikeResponse>> responseEntity = likeController.getLikesByPostId(1L);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(mockLikes, responseEntity.getBody());

        verify(likeService, times(1)).getLikesByPostId(anyLong());
        verifyNoMoreInteractions(likeService);
    }
}
