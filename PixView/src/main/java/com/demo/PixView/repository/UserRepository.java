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

    public Optional<User> selectByUserName(String userName) {
        return jdbiUserRepository.selectByUserName(userName);
    }

    public void deleteUser(Long uerId){
        jdbiUserRepository.deleteUser(uerId);
    }

    public boolean existsById(Long userId) {
        return jdbiUserRepository.existsById(userId);
    }

    public List<User> getAllUsers() {
        return jdbiUserRepository.getAllUsers();
    }

    public List<User> findAll(int offSet, int pageSize) {
        return jdbiUserRepository.findAll(offSet, pageSize);
    }

    public Long countAll() {
        return jdbiUserRepository.countAll();
    }
}
