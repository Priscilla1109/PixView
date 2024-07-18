package com.demo.PixView.mapper;

import com.demo.PixView.model.Like;
import com.demo.PixView.model.LikeResponse;
import com.demo.PixView.model.User;
import com.demo.PixView.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class LikeMapperTest {
    @InjectMocks
    private LikeMapper likeMapper;

    @Mock
    private UserRepository userRepository;

    @Test
    public void testToResponse() {
        LocalDateTime now = LocalDateTime.now();

        Like like = new Like();
        like.setLikeId(1L);
        like.setPostId(2L);
        like.setUserId(3L);
        like.setLocalDateTime(now);

        User user = new User();
        user.setUserId(3L);
        user.setUserName("testUser");

        when(userRepository.selectByUserId(3L)).thenReturn(Optional.of(user));

        LikeResponse response = likeMapper.toResponse(like);

        assertEquals(1L, response.getLikeId());
        assertEquals(2L, response.getPostId());
        assertEquals(3L, response.getUserId());
        assertEquals("testUser", response.getUserName());
        assertEquals(now, response.getLocalDateTime());

        verify(userRepository, times(1)).selectByUserId(3L);
    }
}