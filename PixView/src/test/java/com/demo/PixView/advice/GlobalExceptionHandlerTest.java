package com.demo.PixView.advice;

import com.demo.PixView.exception.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class GlobalExceptionHandlerTest {
    @Mock
    private InvalidAgeException invalidAgeException;

    @Mock
    private UserNameAlreadyExistsException userNameAlreadyExistsException;

    @Mock
    private AlreadyLikedException alreadyLikedException;

    @Mock
    private EmailAlreadyExistsException emailAlreadyExistsException;

    @Mock
    private UserNotFoundException userNotFoundException;

    @Mock
    private PostNotFoundException postNotFoundException;

    @Mock
    private CommentNotFoundException commentNotFoundException;

    @Mock
    private LikeNotFoundException likeNotFoundException;

    @InjectMocks
    private GlobalExceptionHandler globalExceptionHandler;

    @Test
    public void testHandleInvalidAgeException() {
        when(invalidAgeException.getMessage()).thenReturn("Invalid age message");
        ResponseEntity<String> response = globalExceptionHandler.handleInvalidAgeException(invalidAgeException);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Invalid age message", response.getBody());
    }

    @Test
    public void testHandleUserNameAlreadyExistsException() {
        when(userNameAlreadyExistsException.getMessage()).thenReturn("Username already exists message");
        ResponseEntity<String> response = globalExceptionHandler.handleUserNameAlreadyExistsException(userNameAlreadyExistsException);
        assertEquals(HttpStatus.CONFLICT, response.getStatusCode());
        assertEquals("Username already exists message", response.getBody());
    }

    @Test
    public void testHandleAlreadyLikedException() {
        when(alreadyLikedException.getMessage()).thenReturn("Already liked message");
        ResponseEntity<String> response = globalExceptionHandler.handleAlreadyLikedException(alreadyLikedException);
        assertEquals(HttpStatus.CONFLICT, response.getStatusCode());
        assertEquals("Already liked message", response.getBody());
    }

    @Test
    public void testHandleEmailAlreadyExistsException() {
        when(emailAlreadyExistsException.getMessage()).thenReturn("Email already exists message");
        ResponseEntity<String> response = globalExceptionHandler.handleUserNameAlreadyExistsException(emailAlreadyExistsException);
        assertEquals(HttpStatus.CONFLICT, response.getStatusCode());
        assertEquals("Email already exists message", response.getBody());
    }

    @Test
    public void testHandleUserNotFoundException() {
        when(userNotFoundException.getMessage()).thenReturn("User not found message");
        ResponseEntity<String> response = globalExceptionHandler.handleUserNotFoundException(userNotFoundException);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("User not found message", response.getBody());
    }

    @Test
    public void testHandlePostNotFoundException() {
        when(postNotFoundException.getMessage()).thenReturn("Post not found message");
        ResponseEntity<String> response = globalExceptionHandler.handlePostNotFoundException(postNotFoundException);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("Post not found message", response.getBody());
    }

    @Test
    public void testHandleCommentNotFoundException() {
        when(commentNotFoundException.getMessage()).thenReturn("Comment not found message");
        ResponseEntity<String> response = globalExceptionHandler.handleCommentNotFoundException(commentNotFoundException);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("Comment not found message", response.getBody());
    }

    @Test
    public void testHandleLikeNotFoundException() {
        when(likeNotFoundException.getMessage()).thenReturn("Like not found message");
        ResponseEntity<String> response = globalExceptionHandler.handleLikeNotFoundException(likeNotFoundException);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("Like not found message", response.getBody());
    }

}