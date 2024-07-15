package com.demo.PixView.service;

import com.demo.PixView.exception.UserNotFoundException;
import com.demo.PixView.model.User;
import com.demo.PixView.repository.JdbiFriendsRepository;
import com.demo.PixView.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FriendsService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    JdbiFriendsRepository jdbiFriendsRepository;

    public void addNewFriend(Long userId, Long friendId) {
        userRepository.existsById(userId);
        Optional<User> friendOptional = jdbiFriendsRepository.findById(friendId);

        if (friendOptional.isEmpty()) {
            throw new UserNotFoundException("Friend not found with id: " + friendId);
        }
        jdbiFriendsRepository.addNewFriend(userId, friendId);
    }

    public void removeFriend(Long userId, Long friendId) {
        userRepository.existsById(userId);
        Optional<User> friendOptional = jdbiFriendsRepository.findById(friendId);

        if (friendOptional.isEmpty()) {
            throw new UserNotFoundException("Friend not found with id: " + friendId);
        }
        jdbiFriendsRepository.removeFriend(userId, friendId);
    }

    public List<User> getFriends(Long userId) {
        return jdbiFriendsRepository.findFriends(userId);
    }

}
