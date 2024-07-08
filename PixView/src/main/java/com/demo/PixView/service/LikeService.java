package com.demo.PixView.service;

import com.demo.PixView.exception.PostNotFoundException;
import com.demo.PixView.exception.UserNotFoundException;
import com.demo.PixView.model.Like;
import com.demo.PixView.repository.JdbiLikeRepository;
import com.demo.PixView.repository.PostRepository;
import com.demo.PixView.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LikeService {
    @Autowired
    private JdbiLikeRepository likeRepository;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserRepository userRepository;

    public void addLike(Long postId, Long userId) {
        Like like = new Like();
        like.setPostId(postId);
        like.setUserId(userId);

        if (!postRepository.existsById(postId)) {
            throw new PostNotFoundException("Post not found with id: " + postId);
        }
        if (!userRepository.existsById(userId)) {
            throw new UserNotFoundException("User not found with id: " + userId);
        }
        if (!likeRepository.likeExists(like)) {
            likeRepository.addLike(like);
        }
    }

    public void deleteLikeById(Long likeId) {
        Optional<Like> likeOptional = likeRepository.selectByLikeId(likeId);

        if (likeOptional.isPresent()) {
            likeRepository.deleteLikeById(likeId);
        } else {
            throw new UserNotFoundException("User not found");
        }
    }
}
