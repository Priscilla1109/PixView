package com.demo.PixView.controller;

import com.demo.PixView.model.Comment;
import com.demo.PixView.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("APIs/PixView/comments")
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;

    @PostMapping("/add")
    public ResponseEntity<?> addComment(@RequestBody Comment comment) {
        commentService.addComment(
                comment.getPostId(),
                comment.getUserId(),
                comment.getUserName(),
                comment.getContent());
        return ResponseEntity.ok().body(comment);
    }

    @DeleteMapping("/delete/{commentId}")
    public ResponseEntity<String> deleteCommentById(@PathVariable Long commentId) {
        commentService.deleteCommentById(commentId);
        return ResponseEntity.ok("Comment deleted successfully");
    }
}
