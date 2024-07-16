package com.demo.PixView.service;

import com.demo.PixView.exception.InvalidAgeException;
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
        validateBirthDate(user.getBirthDate());

        Long generatedId = repository.createNewUser(user);
        user.setUserId(generatedId);
        return user;
    }

    private void validateBirthDate(LocalDate birthDate) {
        LocalDate today = LocalDate.now();
        int age = Period.between(birthDate, today).getYears();
        if (age < 18) {
            throw new InvalidAgeException("User must be at last 18 years old");
        }
    }

    public Optional<User> getUserById(Long userId) {
        return repository.selectByUserId(userId);
    }

    public void deleteUserById(Long userId) {
        repository.deleteUser(userId);
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
