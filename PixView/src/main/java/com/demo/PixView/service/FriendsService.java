package com.demo.PixView.service;

import com.demo.PixView.exception.UserNotFoundException;
import com.demo.PixView.model.User;
import com.demo.PixView.repository.JdbiFriends;
import com.demo.PixView.repository.JdbiUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FriendsService {
    @Autowired
    JdbiUserRepository jdbiUserRepository;

    @Autowired
    JdbiFriends jdbiFriends;

    public void addNewFriend(Long userId, Long friendId) {
        Optional<User> userOptional = jdbiUserRepository.findById(userId);
        Optional<User> friendOptional = jdbiFriends.findById(friendId);

        if (userOptional.isEmpty()) {
            throw new UserNotFoundException("User not found with id: " + userId);
        }
        if (friendOptional.isEmpty()) {
            throw new UserNotFoundException("Friend not found with id: " + friendId);
        }
        jdbiFriends.addNewFriend(userId, friendId);
    }

    public void removeFriend(Long userId, Long friendId) {
        Optional<User> userOptional = jdbiUserRepository.findById(userId);
        Optional<User> friendOptional = jdbiFriends.findById(friendId);

        if (userOptional.isEmpty()) {
            throw new UserNotFoundException("User not found with id: " + userId);
        }
        if (friendOptional.isEmpty()) {
            throw new UserNotFoundException("Friend not found with id: " + friendId);
        }
        jdbiFriends.removeFriend(userId, friendId);
    }

    public List<User> getFriends(Long userId) {
        return jdbiFriends.findFriends(userId);
    }

}
