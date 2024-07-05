package com.demo.PixView.service;

import com.demo.PixView.model.Like;
import com.demo.PixView.repository.JdbiLikeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LikeService {
    @Autowired
    private JdbiLikeRepository likeRepository;

    public void addLike(Long postId, Long userId) {
        Like like = new Like();
        like.setPostId(postId);
        like.setUserId(userId);

        likeRepository.addLike(like);
    }
}
