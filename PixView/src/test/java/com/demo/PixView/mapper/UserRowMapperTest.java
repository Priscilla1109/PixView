package com.demo.PixView.mapper;

import com.demo.PixView.model.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserRowMapperTest {

    @Mock
    private ResultSet resultSetMock;

    @Test
    public void testMap() throws SQLException {
        UserRowMapper userRowMapper = new UserRowMapper();

        long userId = 1L;
        String userName = "testUser";
        String name = "John Doe";
        LocalDate birthDate = LocalDate.of(1990, 5, 15);
        String email = "john.doe@example.com";
        String password = "password123";

        when(resultSetMock.getLong("user_id")).thenReturn(userId);
        when(resultSetMock.getString("user_name")).thenReturn(userName);
        when(resultSetMock.getString("name")).thenReturn(name);
        when(resultSetMock.getDate("birth_date")).thenReturn(Date.valueOf(birthDate));
        when(resultSetMock.getString("email")).thenReturn(email);
        when(resultSetMock.getString("password")).thenReturn(password);

        User user = userRowMapper.map(resultSetMock, null);

        assertEquals(userId, user.getUserId());
        assertEquals(userName, user.getUserName());
        assertEquals(name, user.getName());
        assertEquals(birthDate, user.getBirthDate());
        assertEquals(email, user.getEmail());
        assertEquals(password, user.getPassword());
        assertEquals(0, user.getFriend().size());

        verify(resultSetMock).getLong("user_id");
        verify(resultSetMock).getString("user_name");
        verify(resultSetMock).getString("name");
        verify(resultSetMock).getDate("birth_date");
        verify(resultSetMock).getString("email");
        verify(resultSetMock).getString("password");
        verifyNoMoreInteractions(resultSetMock);
    }
}
