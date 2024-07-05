package com.demo.PixView.controller;

import com.demo.PixView.model.Post;
import com.demo.PixView.service.LikeService;
import lombok.RequiredArgsConstructor;
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
    public ResponseEntity<?> addLike(@RequestBody Post post){
        service.addLike(post.getPostId(), post.getUserId());
        return ResponseEntity.ok().body(post);
    }
}
