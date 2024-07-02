package com.demo.PixView.service;

import com.demo.PixView.exception.InvalidAgeException;
import com.demo.PixView.exception.UserNameAlreadyExistsException;
import com.demo.PixView.exception.UserNotFoundException;
import com.demo.PixView.model.User;
import com.demo.PixView.repository.JdbiUserRepository;
import com.demo.PixView.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository repository;

    public void createNewUser(User user){
        if (user.getAge() < 18) {
            throw new InvalidAgeException("A idade mínima para cadastro é 18 anos.");
        }

        int count = repository.selectByUserName(user.getUserName());
        if (count > 0) {
            throw new UserNameAlreadyExistsException("O user name já está em uso. Escolha outro user name.");
        }

        long generatedId = repository.createNewUser(user);
        user.setUserId(generatedId);
    }

    public Optional<User> getUserById(Long userId) {
        return repository.selectByUserId(userId);
    }

    public void deleteUserById(Long userId) throws Throwable {
        Optional<User> userOptional = repository.selectByUserId(userId);

        if (userOptional.isPresent()) {
            User user = userOptional.get();

            repository.deleteUser(user.getUserId());
        } else {
            throw new UserNotFoundException("User not found");
        }
    }
}
