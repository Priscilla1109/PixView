package com.demo.PixView.service;

import com.demo.PixView.model.Comment;
import com.demo.PixView.repository.JdbiCommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class CommentService {
    @Autowired
    private JdbiCommentRepository repository;

    public void addComment(Long postId, Long userId, String userName, String content) {
        Comment comment = new Comment();
        comment.setPostId(postId);
        comment.setUserId(userId);
        comment.setUserName(userName);
        comment.setContent(content);
        comment.setLocalDateTime(LocalDateTime.now());

        repository.addComment(comment);
    }
}
