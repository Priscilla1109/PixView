package com.demo.PixView.service;

import com.demo.PixView.exception.PostNotFoundException;
import com.demo.PixView.exception.UserNotFoundException;
import com.demo.PixView.model.Like;
import com.demo.PixView.repository.JdbiLikeRepository;
import com.demo.PixView.repository.PostRepository;
import com.demo.PixView.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LikeService {
    @Autowired
    private JdbiLikeRepository likeRepository;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserRepository userRepository;

    public void addLike(Long postId, Long userId) {
        if (!postRepository.existsById(postId)) {
            throw new PostNotFoundException("Post not found: " + postId);
        }
        if (!userRepository.existsById(userId)) {
            throw new UserNotFoundException("User not found: " + userId);
        }
        Like like = new Like();
        like.setUserId(userId);
        like.setPostId(postId);
        likeRepository.addLike(like);
    }
}
