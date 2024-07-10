package com.demo.PixView.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class UserResponse {
    private Long userId;
    private String name;
    private String userName;
    private int age;
    private LocalDate birthDate;
    private String email;
}
