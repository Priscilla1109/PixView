package com.demo.PixView.controller;

import com.demo.PixView.mapper.UserMapper;
import com.demo.PixView.model.User;
import com.demo.PixView.model.UserPageResponse;
import com.demo.PixView.model.UserResponse;
import com.demo.PixView.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("APIs/PixView/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping
    public ResponseEntity<UserResponse> createNewUser(@RequestBody User user) {
        User userCreated = userService.createNewUser(user);
        UserResponse response = UserMapper.toResponse(userCreated);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserResponse> getUsersById(@PathVariable Long userId) {
        Optional<User> user = userService.getUserById(userId);
        return user.map(value -> ResponseEntity.ok(UserMapper.toResponse(value)))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @GetMapping("/allUsers")
    public ResponseEntity<UserPageResponse> listAllUsers(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int pageSize) {
        UserPageResponse<User> pageUsers = userService.listAllUsers(page, pageSize);

        return ResponseEntity.ok(pageUsers);
    }

    @DeleteMapping("/delete/{userId}")
    public ResponseEntity<String> deleteUser(@PathVariable Long userId) throws Throwable {
        userService.deleteUserById(userId);
        return ResponseEntity.ok("User deleted successfully");
    }
}
