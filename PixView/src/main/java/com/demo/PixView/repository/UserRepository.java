package com.demo.PixView.repository;

import com.demo.PixView.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class UserRepository {
    private final JdbiUserRepository jdbiUserRepository;

    public Long createNewUser(final User user) {
        return jdbiUserRepository.createNewUser(user);
    }

    public Optional<User> selectByUserId(Long userId) {
        return jdbiUserRepository.selectUserById(userId);
    }

    public int selectByUserName(String userName) {
        return jdbiUserRepository.selectByUserName(userName);
    }

    public void deleteUser(Long uerId){
        jdbiUserRepository.deleteUser(uerId);
    }
}