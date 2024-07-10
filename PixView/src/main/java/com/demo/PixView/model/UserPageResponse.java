package com.demo.PixView.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class UserPageResponse<T>{
    private List<UserResponse> users;
    private Meta meta;
}
