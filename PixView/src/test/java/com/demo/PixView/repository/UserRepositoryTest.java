package com.demo.PixView.repository;

import com.demo.PixView.exception.EmailAlreadyExistsException;
import com.demo.PixView.exception.UserNameAlreadyExistsException;
import com.demo.PixView.exception.UserNotFoundException;
import com.demo.PixView.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserRepositoryTest {
    @Mock
    private JdbiUserRepository jdbiUserRepository;

    @InjectMocks
    private UserRepository userRepository;

    private User sampleUser;

    @BeforeEach
    public void setUp() {
        sampleUser = new User();
        sampleUser.setUserId(1L);
        sampleUser.setUserName("testUser");
        sampleUser.setEmail("test@example.com");
    }

    @Test
    void testCreateNewUser() {
        when(jdbiUserRepository.selectByUserName(sampleUser.getUserName())).thenReturn(Optional.empty());
        when(jdbiUserRepository.selectByEmail(sampleUser.getEmail())).thenReturn(Optional.empty());
        when(jdbiUserRepository.createNewUser(sampleUser)).thenReturn(1L);

        Long userId = userRepository.createNewUser(sampleUser);

        assertEquals(1L, userId);
        verify(jdbiUserRepository, times(1)).selectByUserName(sampleUser.getUserName());
        verify(jdbiUserRepository, times(1)).selectByEmail(sampleUser.getEmail());
        verify(jdbiUserRepository, times(1)).createNewUser(sampleUser);
    }

    @Test
    void testSelectByUserId() {
        when(jdbiUserRepository.selectUserById(1L)).thenReturn(Optional.of(sampleUser));

        Optional<User> user = userRepository.selectByUserId(1L);

        assertTrue(user.isPresent());
        assertEquals(sampleUser, user.get());
        verify(jdbiUserRepository, times(1)).selectUserById(1L);
    }

    @Test
    void testSelectByUserId_NotFound() {
        when(jdbiUserRepository.selectUserById(1L)).thenReturn(Optional.empty());

        Exception exception = assertThrows(UserNotFoundException.class, () -> {
            userRepository.selectByUserId(1L);
        });

        assertEquals("User not found with id: 1", exception.getMessage());
        verify(jdbiUserRepository, times(1)).selectUserById(1L);
    }

    @Test
    void testSelectByUserName() {
        when(jdbiUserRepository.selectByUserName("testUser")).thenReturn(Optional.of(sampleUser));

        Optional<User> user = userRepository.selectByUserName("testUser");

        assertTrue(user.isPresent());
        assertEquals(sampleUser, user.get());
        verify(jdbiUserRepository, times(1)).selectByUserName("testUser");
    }

    @Test
    void testSelectByUserName_NotFound() {
        when(jdbiUserRepository.selectByUserName("testUser")).thenReturn(Optional.empty());

        Exception exception = assertThrows(UserNotFoundException.class, () -> {
            userRepository.selectByUserName("testUser");
        });

        assertEquals("User not found with name: testUser", exception.getMessage());
        verify(jdbiUserRepository, times(1)).selectByUserName("testUser");
    }

    @Test
    void testExistByUserName_UserNameExists() {
        when(jdbiUserRepository.selectByUserName("testUser")).thenReturn(Optional.of(sampleUser));

        Exception exception = assertThrows(UserNameAlreadyExistsException.class, () -> {
            userRepository.createNewUser(sampleUser);
        });

        assertEquals("User name already exists: testUser", exception.getMessage());
        verify(jdbiUserRepository, times(1)).selectByUserName("testUser");
    }

    @Test
    void testExistByEmail_EmailExists() {
        when(jdbiUserRepository.selectByEmail("test@example.com")).thenReturn(Optional.of(sampleUser));

        Exception exception = assertThrows(EmailAlreadyExistsException.class, () -> {
            userRepository.createNewUser(sampleUser);
        });

        assertEquals("Email already begin used: test@example.com", exception.getMessage());
        verify(jdbiUserRepository, times(1)).selectByEmail("test@example.com");
    }

    @Test
    void testDeleteUser() {
        when(jdbiUserRepository.selectUserById(1L)).thenReturn(Optional.of(sampleUser));
        doNothing().when(jdbiUserRepository).deleteUser(1L);

        userRepository.deleteUser(1L);

        verify(jdbiUserRepository, times(1)).selectUserById(1L);
        verify(jdbiUserRepository, times(1)).deleteUser(1L);
    }

    @Test
    void testFindAll() {
        List<User> users = new ArrayList<>();
        users.add(sampleUser);

        when(jdbiUserRepository.findAll(0, 10)).thenReturn(users);

        List<User> result = userRepository.findAll(0, 10);

        assertEquals(1, result.size());
        assertEquals(sampleUser, result.get(0));
        verify(jdbiUserRepository, times(1)).findAll(0, 10);
    }

    @Test
    void testCountAll() {
        when(jdbiUserRepository.countAll()).thenReturn(1L);

        Long count = userRepository.countAll();

        assertEquals(1L, count);
        verify(jdbiUserRepository, times(1)).countAll();
    }
}
