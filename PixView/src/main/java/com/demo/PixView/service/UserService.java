package com.demo.PixView.service;

import com.demo.PixView.exception.InvalidAgeException;
import com.demo.PixView.exception.UserNameAlreadyExistsException;
import com.demo.PixView.model.User;
import com.demo.PixView.repository.JdbiUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private JdbiUserRepository jdbiUserRepository;

    public void createNewUser(User user){
        if (user.getAge() < 18) {
            throw new InvalidAgeException("A idade mínima para cadastro é 18 anos.");
        }

        int count = jdbiUserRepository.selectByUserName(user.getUserName());
        if (count > 0) {
            throw new UserNameAlreadyExistsException("O userName já está em uso. Escolha outro userName.");
        }

        long generatedId = jdbiUserRepository.createNewUser(user);
        user.setId(generatedId);
    }
}
