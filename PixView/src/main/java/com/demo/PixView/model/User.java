package com.demo.PixView.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private Long userId;
    private String name;
    private String userName;
    private int age;
    private String email;
    private String password;
    private List<User> friend;
}
