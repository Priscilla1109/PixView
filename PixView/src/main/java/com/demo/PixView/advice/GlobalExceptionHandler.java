package com.demo.PixView.advice;

import com.demo.PixView.exception.InvalidAgeException;
import com.demo.PixView.exception.UserNameAlreadyExistsException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

//Tratamento global de exceções
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(InvalidAgeException.class)
    public ResponseEntity<String> handleInvalidAgeException(InvalidAgeException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    @ExceptionHandler(UserNameAlreadyExistsException.class)
    public ResponseEntity<String> handleUserNameAlreadyExistsException(UserNameAlreadyExistsException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
    }
}