package com.demo.PixView.service;

import com.demo.PixView.exception.CommentNotFoundException;
import com.demo.PixView.mapper.CommentMapper;
import com.demo.PixView.model.Comment;
import com.demo.PixView.model.CommentResponse;
import com.demo.PixView.model.User;
import com.demo.PixView.repository.JdbiCommentRepository;
import com.demo.PixView.repository.PostRepository;
import com.demo.PixView.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CommentService {
    @Autowired
    private JdbiCommentRepository commentRepository;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserRepository userRepository;

    public Comment addComment(Long postId, Long userId, String content) {
        Comment comment = new Comment();
        comment.setPostId(postId);
        comment.setUserId(userId);
        comment.setContent(content);
        comment.setLocalDateTime(LocalDateTime.now());

        Optional<User> user = userRepository.selectByUserId(userId);
        comment.setUserName(user.get().getUserName());

        Long generatedId = commentRepository.addComment(comment);
        comment.setCommentId(generatedId);

        return comment;
    }

    public void deleteCommentById(Long commentId) {
        Optional<Comment> commentOptional = commentRepository.selectByCommentId(commentId);

        if (commentOptional.isPresent()) {
            Comment comment = commentOptional.get();

            commentRepository.deleteCommentById(comment.getUserId());
        } else {
            throw new CommentNotFoundException("Comment not found with id: " + commentId);
        }
    }

    public List<CommentResponse> getCommentsByPostId(Long postId) {
        validatePostExists(postId);

        List<Comment> comments = commentRepository.findCommentsByPostId(postId);
        List<CommentResponse> commentResponses = new ArrayList<>();

        for (Comment comment : comments) {
            CommentResponse commentResponse = CommentMapper.toResponse(comment);
            commentResponses.add(commentResponse);
        }

        return commentResponses;
    }

    private void validatePostExists(Long postId) {
        postRepository.selectPostsById(postId);
    }
}
