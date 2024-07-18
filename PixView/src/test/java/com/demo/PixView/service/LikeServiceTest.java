package com.demo.PixView.service;

import com.demo.PixView.exception.AlreadyLikedException;
import com.demo.PixView.exception.LikeNotFoundException;
import com.demo.PixView.mapper.LikeMapper;
import com.demo.PixView.model.Like;
import com.demo.PixView.model.LikeResponse;
import com.demo.PixView.model.Post;
import com.demo.PixView.model.User;
import com.demo.PixView.repository.JdbiLikeRepository;
import com.demo.PixView.repository.PostRepository;
import com.demo.PixView.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.lang.reflect.Method;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class LikeServiceTest {
    @InjectMocks
    private LikeService likeService;

    @Mock
    private JdbiLikeRepository likeRepository;

    @Mock
    private PostRepository postRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private LikeMapper likeMapper;


    @Test
    public void testAddLike_Successful() {
        Long postId = 1L;
        Long userId = 1L;
        LocalDateTime now = LocalDateTime.now();

        when(postRepository.selectPostsById(postId)).thenReturn(Optional.of(new Post()));
        when(userRepository.selectByUserId(userId)).thenReturn(Optional.of(new User()));

        when(likeRepository.existsByPostIdAndUserId(postId, userId)).thenReturn(false);

        when(likeRepository.addLike(any())).thenAnswer(invocation -> {
            Like like = invocation.getArgument(0);
            like.setLikeId(1L);
            return 1L;
        });

        likeService.addLike(postId, userId);

        verify(postRepository, times(1)).selectPostsById(postId);
        verify(userRepository, times(1)).selectByUserId(userId);
        verify(likeRepository, times(1)).existsByPostIdAndUserId(postId, userId);
        verify(likeRepository, times(1)).addLike(argThat(like -> like.getPostId().equals(postId) && like.getUserId().equals(userId)));
    }

    @Test
    public void testDeleteLikeById_Successful() {
        Long likeId = 1L;

        when(likeRepository.selectByLikeId(likeId)).thenReturn(Optional.of(new Like()));

        likeService.deleteLikeById(likeId);

        verify(likeRepository, times(1)).selectByLikeId(likeId);
        verify(likeRepository, times(1)).deleteLikeById(likeId);
    }

    @Test
    public void testDeleteLikeById_LikeNotFound() {
        Long likeId = 1L;

        when(likeRepository.selectByLikeId(likeId)).thenReturn(Optional.empty());

        assertThrows(LikeNotFoundException.class, () -> likeService.deleteLikeById(likeId));

        verify(likeRepository, times(1)).selectByLikeId(likeId);
        verify(likeRepository, never()).deleteLikeById(any());
    }

    @Test
    public void testGetLikesByPostId_Successful() {
        Long postId = 1L;

        List<Like> likes = new ArrayList<>();
        likes.add(new Like());
        when(likeRepository.findLikesByPostId(postId)).thenReturn(likes);

        List<LikeResponse> expectedResponses = new ArrayList<>();
        expectedResponses.add(new LikeResponse());
        when(likeMapper.toResponse(any())).thenReturn(new LikeResponse());

        List<LikeResponse> result = likeService.getLikesByPostId(postId);

        assertEquals(expectedResponses.size(), result.size());

        verify(postRepository, times(1)).selectPostsById(postId);
        verify(likeRepository, times(1)).findLikesByPostId(postId);
        verify(likeMapper, times(likes.size())).toResponse(any());
    }

    @Test
    public void testValidateUserExists() throws Exception {
        Long userId = 1L;
        when(userRepository.selectByUserId(userId)).thenReturn(Optional.of(new User()));

        Method method = LikeService.class.getDeclaredMethod("validateUserExists", Long.class);
        method.setAccessible(true);
        method.invoke(likeService, userId);

        verify(userRepository, times(1)).selectByUserId(userId);
    }

    @Test
    public void testValidatePostExists() throws Exception {
        Long postId = 1L;
        when(postRepository.selectPostsById(postId)).thenReturn(Optional.of(new Post()));

        Method method = LikeService.class.getDeclaredMethod("validatePostExists", Long.class);
        method.setAccessible(true);
        method.invoke(likeService, postId);

        verify(postRepository, times(1)).selectPostsById(postId);
    }

    @Test
    public void testValidateLikeExists_NotLikedYet() throws Exception {
        Long postId = 1L;
        Long userId = 1L;
        when(likeRepository.existsByPostIdAndUserId(postId, userId)).thenReturn(false);

        Method method = LikeService.class.getDeclaredMethod("validateLikeExists", Long.class, Long.class);
        method.setAccessible(true);
        method.invoke(likeService, postId, userId);

        verify(likeRepository, times(1)).existsByPostIdAndUserId(postId, userId);
    }

    @Test
    void testAddLike_LikeAlreadyExists() {
        Long postId = 1L;
        Long userId = 1L;

        when(postRepository.selectPostsById(postId)).thenReturn(Optional.of(new Post()));
        when(userRepository.selectByUserId(userId)).thenReturn(Optional.of(new User()));

        when(likeRepository.existsByPostIdAndUserId(postId, userId)).thenReturn(true);

        assertThrows(AlreadyLikedException.class, () -> likeService.addLike(postId, userId));

        verify(postRepository, times(1)).selectPostsById(postId);
        verify(userRepository, times(1)).selectByUserId(userId);
        verify(likeRepository, times(1)).existsByPostIdAndUserId(postId, userId);
        verify(likeRepository, never()).addLike(any()); // Verifica se addLike nunca foi chamado
    }

}