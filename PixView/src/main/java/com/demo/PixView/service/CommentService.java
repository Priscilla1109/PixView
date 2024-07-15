package com.demo.PixView.service;

import com.demo.PixView.exception.CommentNotFoundException;
import com.demo.PixView.model.Comment;
import com.demo.PixView.repository.JdbiCommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class CommentService {
    @Autowired
    private JdbiCommentRepository repository;

    public Comment addComment(Long postId, Long userId, String userName, String content) {
        Comment comment = new Comment();
        comment.setPostId(postId);
        comment.setUserId(userId);
        comment.setUserName(userName);
        comment.setContent(content);
        comment.setLocalDateTime(LocalDateTime.now());

        Long generatedId = repository.addComment(comment);
        comment.setCommentId(generatedId);

        return comment;
    }

    public void deleteCommentById(Long commentId) {
        Optional<Comment> commentOptional = repository.selectByCommentId(commentId);

        if (commentOptional.isPresent()) {
            Comment comment = commentOptional.get();

            repository.deleteCommentById(comment.getUserId());
        } else {
            throw new CommentNotFoundException("Comment not found with id: " + commentId);
        }
    }
}
