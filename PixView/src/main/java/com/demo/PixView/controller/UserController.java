package com.demo.PixView.controller;

import com.demo.PixView.model.User;
import com.demo.PixView.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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

    @GetMapping("/{userId}")
    public ResponseEntity<Optional<User>> getUsersById(@PathVariable Long userId) {
        Optional<User> users = userService.getUserById(userId);
        if (users.isEmpty()){
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(users);
        }
    }

    @DeleteMapping("/delete/{userId}")
    public ResponseEntity<String> deleteUser(@PathVariable Long userId) throws Throwable {
        userService.deleteUserById(userId);
        return ResponseEntity.ok("User deleted successfully");
    }
}
