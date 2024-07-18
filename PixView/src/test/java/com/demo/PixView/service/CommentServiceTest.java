package com.demo.PixView.service;

import com.demo.PixView.exception.CommentNotFoundException;
import com.demo.PixView.model.Comment;
import com.demo.PixView.repository.JdbiCommentRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CommentServiceTest {
    @Mock
    private JdbiCommentRepository repositoryMock;

    @InjectMocks
    private CommentService commentService;

    @Test
    public void testAddComment() {
        Long postId = 1L;
        Long userId = 1L;
        String userName = "testUser";
        String content = "Test comment content";

        Comment commentToAdd = new Comment();
        commentToAdd.setPostId(postId);
        commentToAdd.setUserId(userId);
        commentToAdd.setUserName(userName);
        commentToAdd.setContent(content);
        commentToAdd.setLocalDateTime(LocalDateTime.now());

        Long generatedId = 1L;
        when(repositoryMock.addComment(any(Comment.class))).thenReturn(generatedId);

        Comment addedComment = commentService.addComment(postId, userId, content);

        assertEquals(generatedId, addedComment.getCommentId());
        assertEquals(postId, addedComment.getPostId());
        assertEquals(userId, addedComment.getUserId());
        assertEquals(userName, addedComment.getUserName());
        assertEquals(content, addedComment.getContent());
        //assertEquals(commentToAdd.getLocalDateTime(), addedComment.getLocalDateTime());

        verify(repositoryMock, times(1)).addComment(any(Comment.class));
        verifyNoMoreInteractions(repositoryMock);
    }

    @Test
    public void testDeleteCommentById_ExistingComment() {
        Long commentId = 1L;
        Long userId = 1L;

        Comment mockComment = new Comment();
        mockComment.setCommentId(commentId);
        mockComment.setUserId(userId);

        when(repositoryMock.selectByCommentId(commentId)).thenReturn(Optional.of(mockComment));

        commentService.deleteCommentById(commentId);

        verify(repositoryMock, times(1)).selectByCommentId(commentId);
        verify(repositoryMock, times(1)).deleteCommentById(userId);
        verifyNoMoreInteractions(repositoryMock);
    }

    @Test
    public void testDeleteCommentById_CommentNotFound() {
        Long commentId = 1L;

        when(repositoryMock.selectByCommentId(commentId)).thenReturn(Optional.empty());

        CommentNotFoundException exception = assertThrows(CommentNotFoundException.class, () -> {
            commentService.deleteCommentById(commentId);
        });

        assertEquals("Comment not found with id: " + commentId, exception.getMessage());

        verify(repositoryMock, times(1)).selectByCommentId(commentId);
        verifyNoMoreInteractions(repositoryMock);
    }
}
