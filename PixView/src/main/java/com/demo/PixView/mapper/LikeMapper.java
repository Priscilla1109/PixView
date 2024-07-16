package com.demo.PixView.mapper;

import com.demo.PixView.exception.UserNotFoundException;
import com.demo.PixView.model.Like;
import com.demo.PixView.model.LikeResponse;
import com.demo.PixView.model.User;
import com.demo.PixView.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class LikeMapper {

    @Autowired
    private UserRepository userRepository;

    public LikeResponse toResponse(Like like) {
        LikeResponse response = new LikeResponse();
        response.setLikeId(like.getLikeId());
        response.setPostId(like.getPostId());
        response.setUserId(like.getUserId());
        response.setLocalDateTime(like.getLocalDateTime());

        Optional<User> user = userRepository.selectByUserId(like.getUserId());

        response.setUserName(user.get().getUserName());

        return response;
    }
}