package com.demo.PixView.service;

import com.demo.PixView.model.User;
import com.demo.PixView.repository.JdbiUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private JdbiUserRepository jdbiUserRepository;

    public void createNewUser(User user){
        jdbiUserRepository.createNewUser(user);
    }
}
