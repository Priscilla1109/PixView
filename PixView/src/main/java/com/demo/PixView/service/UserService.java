package com.demo.PixView.service;

import com.demo.PixView.exception.InvalidAgeException;
import com.demo.PixView.exception.UserNameAlreadyExistsException;
import com.demo.PixView.exception.UserNotFoundException;
import com.demo.PixView.model.User;
import com.demo.PixView.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository repository;

    public void createNewUser(User user){
        Optional<User> existingUser = repository.selectByUserName(user.getUserName());
        if (existingUser.isPresent()) {
            throw new UserNameAlreadyExistsException("User name already exists: " + user.getUserName());
        }

        LocalDate today = LocalDate.now();
        LocalDate birthDate = user.getBirthDate();
        int age = Period.between(birthDate, today).getYears();
        if (age < 18) {
            throw new InvalidAgeException("User must be at lest 18 years old");
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
