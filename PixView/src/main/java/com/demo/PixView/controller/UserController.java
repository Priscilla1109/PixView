package com.demo.PixView.controller;

import com.demo.PixView.model.User;
import com.demo.PixView.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("APIs/PixView/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping
    public ResponseEntity<User> createNewUser(@RequestBody User user) {
        userService.createNewUser(user);

        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }
}
