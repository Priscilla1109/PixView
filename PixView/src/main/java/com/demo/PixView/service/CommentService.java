package com.demo.PixView.service;

import com.demo.PixView.exception.CommentNotFoundException;
import com.demo.PixView.exception.UserNotFoundException;
import com.demo.PixView.model.Comment;
import com.demo.PixView.model.User;
import com.demo.PixView.repository.JdbiCommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

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

    public void deleteCommentById(Long commentId) {
        Optional<Comment> commentOptional = repository.selectByCommentId(commentId);

        if (commentOptional.isPresent()) {
            Comment comment = commentOptional.get();

            repository.deleteCommentById(comment.getUserId());
        } else {
            throw new UserNotFoundException("User not found");
        }
    }
}
