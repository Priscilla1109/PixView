package com.demo.PixView.mapper;

import com.demo.PixView.model.User;
import com.demo.PixView.model.UserResponse;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.stream.Collectors;

public class UserMapper {
    public static UserResponse toResponse(User user) {
        int age = calculateAge(user.getBirthDate());
        return new UserResponse(
                user.getUserId(),
                user.getUserName(),
                user.getName(),
                age,
                user.getBirthDate(),
                user.getEmail()
        );
    }

    public static List<UserResponse> toResponseList(List<User> user) {
        return user.stream()
                .map(UserMapper::toResponse)
                .collect(Collectors.toList());
    }

    public static int calculateAge(LocalDate birthDate) {
        if (birthDate == null) {
            return 0;
        }
        return Period.between(birthDate, LocalDate.now()).getYears();
    }
}
