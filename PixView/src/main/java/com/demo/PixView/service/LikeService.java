package com.demo.PixView.service;

import com.demo.PixView.exception.AlreadyLikedException;
import com.demo.PixView.exception.LikeNotFoundException;
import com.demo.PixView.mapper.LikeMapper;
import com.demo.PixView.model.Like;
import com.demo.PixView.model.LikeResponse;
import com.demo.PixView.repository.JdbiLikeRepository;
import com.demo.PixView.repository.PostRepository;
import com.demo.PixView.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class LikeService {
    @Autowired
    private JdbiLikeRepository likeRepository;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private LikeMapper likeMapper;

    public void addLike(Long postId, Long userId) {
        validatePostExists(postId);
        validateUserExists(userId);
        validateLikeExists(postId, userId);

        Like like = new Like();
        like.setPostId(postId);
        like.setUserId(userId);
        like.setLocalDateTime(LocalDateTime.now());

        if (!likeRepository.likeExists(like)) {
            long generatedId = likeRepository.addLike(like);
            like.setLikeId(generatedId);
        }
    }

    public void deleteLikeById(Long likeId) {
        Optional<Like> likeOptional = likeRepository.selectByLikeId(likeId);

        if (likeOptional.isPresent()) {
            likeRepository.deleteLikeById(likeId);
        } else {
            throw new LikeNotFoundException("Like not found with id: " + likeId);
        }
    }

    public List<LikeResponse> getLikesByPostId(Long postId) {
        validatePostExists(postId);

        List<Like> likes = likeRepository.findLikesByPostId(postId);
        List<LikeResponse> likeResponses = new ArrayList<>();

        for (Like like : likes) {
            LikeResponse likeResponse = likeMapper.toResponse(like);
            likeResponses.add(likeResponse);
        }

        return likeResponses;
    }

    private void validateUserExists(Long userId) {
        userRepository.selectByUserId(userId);
    }

    private void validatePostExists(Long postId) {
        postRepository.selectPostsById(postId);
    }

    private void validateLikeExists(Long postId, Long userId) {
        if (likeRepository.existsByPostIdAndUserId(postId, userId)) {
            throw new AlreadyLikedException("User already liked this post");
        }
    }
}
