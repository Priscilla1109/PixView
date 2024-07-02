package com.demo.PixView.controller;

import com.demo.PixView.model.Post;
import com.demo.PixView.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("APIs/PixView/posts")
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;

    @PostMapping
    public ResponseEntity<Post> createNewPost(@RequestBody Post post) {
        postService.createNewPost(post);

        return ResponseEntity.status(HttpStatus.CREATED).body(post);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Post>> getPostsByUserId(@PathVariable Long userId) {
        List<Post> posts = postService.getPostsByUserId(userId);
        if (posts.isEmpty()){
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(posts);
        }
    }
}
