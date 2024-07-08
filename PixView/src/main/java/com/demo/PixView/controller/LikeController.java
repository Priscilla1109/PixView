package com.demo.PixView.controller;

import com.demo.PixView.exception.PostNotFoundException;
import com.demo.PixView.exception.UserNotFoundException;
import com.demo.PixView.model.Like;
import com.demo.PixView.model.Post;
import com.demo.PixView.service.LikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("APIs/PixView/likes")
@RequiredArgsConstructor
public class LikeController {
    private final LikeService service;

    @PostMapping("/add")
    public ResponseEntity<?> addLike(@RequestBody Like like){
        try {
            service.addLike(like.getLikeId(), like.getPostId());
            return ResponseEntity.ok("Like added successfully");
        } catch (PostNotFoundException | UserNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An unexpected error occurred");
        }

    }
}
