package com.demo.PixView.controller;

import com.demo.PixView.exception.UserNotFoundException;
import com.demo.PixView.model.User;
import com.demo.PixView.model.Friends;
import com.demo.PixView.service.FriendsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("APIs/PixView/friends")
@RequiredArgsConstructor
public class FriendsController {
    private final FriendsService service;

    @PostMapping("/add")
    public ResponseEntity<String> addNewFriend(@RequestBody Friends friends) {
        try {
            service.addNewFriend(friends.getUserId(), friends.getFriendId());
            return  ResponseEntity.ok("Friend added successfully");
        } catch (UserNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> removeFriend(@RequestBody Friends friends) {
        try {
            service.removeFriend(friends.getUserId(), friends.getFriendId());
            return ResponseEntity.ok("Friend removed successfully");
        } catch (UserNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping("/list/{userId}")
    public ResponseEntity<?> getFriends(@PathVariable Long userId) {
        try {
            List<User> friends = service.getFriends(userId);
            return ResponseEntity.ok(friends);
        } catch (UserNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An unexpected error occurred");
        }
    }
}
