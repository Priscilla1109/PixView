package com.demo.PixView.service;

import com.demo.PixView.exception.InvalidAgeException;
import com.demo.PixView.exception.UserNameAlreadyExistsException;
import com.demo.PixView.exception.UserNotFoundException;
import com.demo.PixView.model.Meta;
import com.demo.PixView.model.User;
import com.demo.PixView.model.UserPageResponse;
import com.demo.PixView.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository repository;

    public User createNewUser(User user){
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

        Long generatedId = repository.createNewUser(user);
        user.setUserId(generatedId);
        return user;
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

    public UserPageResponse<User> listAllUsers(int page, int pageSize) {
        int offSet = page * pageSize;

        List<User> users = repository.findAll(offSet, pageSize);
        Long totalElements = repository.countAll();
        int totalPages = (int) Math.ceil((double) totalElements / pageSize);

        return new UserPageResponse(users,
                new Meta(page, pageSize, totalPages, totalElements));
    }
}
