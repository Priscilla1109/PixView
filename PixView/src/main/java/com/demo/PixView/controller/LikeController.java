package com.demo.PixView.controller;

import com.demo.PixView.model.Post;
import com.demo.PixView.service.LikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("APIs/PixView/likes")
@RequiredArgsConstructor
public class LikeController {
    private final LikeService service;

    @PostMapping("/add")
    public ResponseEntity<?> addLike(@RequestBody Post post){
        service.addLike(post.getPostId(), post.getUserId());
        return ResponseEntity.ok("Like added successfully");
    }

    @DeleteMapping("/delete/{likeId}")
    public ResponseEntity<String> deleteLikeById(@PathVariable Long likeId) {
        service.deleteLikeById(likeId);
        return ResponseEntity.ok("Like deleted successfully");
    }
}
