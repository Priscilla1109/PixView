package com.demo.PixView.controller;

import com.demo.PixView.mapper.PostMapper;
import com.demo.PixView.model.Post;
import com.demo.PixView.model.PostPageResponse;
import com.demo.PixView.model.PostRequest;
import com.demo.PixView.model.PostResponse;
import com.demo.PixView.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("APIs/PixView/posts")
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;

    @PostMapping
    public ResponseEntity<PostResponse> createNewPost(@RequestBody PostRequest postRequest) {
        Post post = postService.createNewPost(postRequest.getUserName(), postRequest.getContent());

        return ResponseEntity.status(HttpStatus.CREATED).body(PostMapper.toResponse(post));
    }

    @GetMapping("/{postId}")
    public ResponseEntity<PostResponse> getPostsById(@PathVariable Long postId) {
        Optional<Post> posts = postService.getPostsById(postId);

        return posts.map(value -> ResponseEntity.ok(PostMapper.toResponse(value)))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<PostResponse>> getPostsByUserId(@PathVariable Long userId) {
        List<Post> posts = postService.getPostsByUserId(userId);
        List<PostResponse> postResponses = posts.stream()
                .map(PostResponse::toResponse)
                .collect(Collectors.toList());
        return ResponseEntity.ok(postResponses);
    }

    @GetMapping("/allPosts")
    public ResponseEntity<PostPageResponse> listAllPosts(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int pageSize) {
        PostPageResponse<Post> postPageResponse = postService.listAllPosts(page, pageSize);

        return ResponseEntity.ok(postPageResponse);
    }

    @DeleteMapping("/delete/{postId}")
    public ResponseEntity<String> deletePost(@PathVariable Long postId){
        postService.deletePostById(postId);
        return ResponseEntity.ok("Post deleted successfully");
    }
}
