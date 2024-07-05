package com.demo.PixView.controller;

import com.demo.PixView.model.Post;
import com.demo.PixView.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("APIs/PixView/posts")
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;

    @PostMapping
    public ResponseEntity<Post> createNewPost(@RequestBody Post post) {
        postService.createNewPost(post.getUserName(), post.getContent());

        return ResponseEntity.status(HttpStatus.CREATED).body(post);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<Optional<Post>> getPostsByUserId(@PathVariable Long userId) {
        Optional<Post> posts = postService.getPostsByUserId(userId);
        if (posts.isEmpty()){
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(posts);
        }
    }

    @DeleteMapping("/delete/{postId}")
    public ResponseEntity<String> deletePost(@PathVariable Long postId){
        postService.deletePostById(postId);
        return ResponseEntity.ok("Post deleted successfully");
    }
}
