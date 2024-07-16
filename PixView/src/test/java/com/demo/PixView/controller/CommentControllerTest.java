package com.demo.PixView.controller;

import com.demo.PixView.model.Comment;
import com.demo.PixView.model.CommentResponse;
import com.demo.PixView.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.bind.annotation.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
public class CommentControllerTest {
    @Mock
    private CommentService commentService;

    @InjectMocks
    private CommentController commentController;

    @Test
    public void testAddComment() {
        Comment mockComment = new Comment();
        mockComment.setPostId(1L);
        mockComment.setUserId(1L);
        mockComment.setUserName("Priscilla_Fonseca");
        mockComment.setContent("Test comment");

        when(commentService.addComment(anyLong(), anyLong(), anyString(), anyString())).thenReturn(mockComment);

        Comment requestComment = new Comment();
        requestComment.setPostId(1L);
        requestComment.setUserId(1L);
        requestComment.setUserName("Priscilla_Fonseca");
        requestComment.setContent("Test comment");

        ResponseEntity<CommentResponse> responseEntity = commentController.addComment(requestComment);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        CommentResponse responseBody = responseEntity.getBody();
        assertEquals(mockComment.getContent(), responseBody.getContent());
        assertEquals(mockComment.getUserName(), responseBody.getUserName());

        verify(commentService, times(1)).addComment(anyLong(), anyLong(), anyString(), anyString());
        verifyNoMoreInteractions(commentService);
    }

    @Test
    public void testDeleteCommentById() {
        Long commentId = 1L;
        doNothing().when(commentService).deleteCommentById(commentId);

        ResponseEntity<String> responseEntity = commentController.deleteCommentById(commentId);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("Comment deleted successfully", responseEntity.getBody());

        verify(commentService, times(1)).deleteCommentById(commentId);
        verifyNoMoreInteractions(commentService);
    }
}
