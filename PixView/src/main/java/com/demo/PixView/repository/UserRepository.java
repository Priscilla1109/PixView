package com.demo.PixView.repository;

import com.demo.PixView.exception.EmailAlreadyExistsException;
import com.demo.PixView.exception.UserNameAlreadyExistsException;
import com.demo.PixView.exception.UserNotFoundException;
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
        existByUserName(user.getUserName());
        existByEmail(user.getEmail());

        return jdbiUserRepository.createNewUser(user);
    }

    public Optional<User> selectByUserId(Long userId) {
        Optional<User> optionalUser = jdbiUserRepository.selectUserById(userId);
        return Optional.ofNullable(optionalUser.orElseThrow(() -> new UserNotFoundException("User not found with id: " + userId)));
    }

    public Optional<User> selectByUserName(String userName) {
        Optional<User> optionalUser = jdbiUserRepository.selectByUserName(userName);
        return Optional.ofNullable(optionalUser.orElseThrow(() -> new UserNotFoundException("User not found with name: " + userName)));
    }

    private Optional<User> existByUserName(String userName) {
        Optional<User> existingUser = jdbiUserRepository.selectByUserName(userName);
        if (existingUser.isPresent()) {
            throw new UserNameAlreadyExistsException("User name already exists: " + userName);
        }
        return existingUser;
    }

    private Optional<User> existByEmail(String email) {
        Optional<User> existingUser = jdbiUserRepository.selectByEmail(email);
        if (existingUser.isPresent()) {
            throw new EmailAlreadyExistsException("Email already begin used: " + email);
        }
        return existingUser;
    }

    public void deleteUser(Long userId){
        selectByUserId(userId);
        jdbiUserRepository.deleteUser(userId);
    }

    public List<User> findAll(int offSet, int pageSize) {
        return jdbiUserRepository.findAll(offSet, pageSize);
    }

    public Long countAll() {
        return jdbiUserRepository.countAll();
    }
}
