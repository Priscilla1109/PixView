package com.demo.PixView.repository;

import com.demo.PixView.exception.PostNotFoundException;
import com.demo.PixView.model.Post;
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
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PostRepositoryTest {
    @Mock
    private JdbiPostRepository jdbiPostRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private JdbiLikeRepository likeRepository;

    @Mock
    private JdbiCommentRepository commentRepository;

    @InjectMocks
    private PostRepository postRepository;

    private Post samplePost;

    @BeforeEach
    void setUp() {
        samplePost = new Post();
        samplePost.setPostId(1L);
        samplePost.setUserId(1L);
        samplePost.setContent("Sample content");
    }

    @Test
    void testCreateNewPost() {
        when(jdbiPostRepository.createNewPost(any(Post.class))).thenReturn(1L);

        Long postId = postRepository.createNewPost(samplePost);

        assertEquals(1L, postId);
        verify(jdbiPostRepository, times(1)).createNewPost(any(Post.class));
    }

    @Test
    void testSelectPostsById_PostExists() {
        when(jdbiPostRepository.selectPostsById(1L)).thenReturn(Optional.of(samplePost));

        Optional<Post> post = postRepository.selectPostsById(1L);

        assertTrue(post.isPresent());
        assertEquals(samplePost, post.get());
        verify(jdbiPostRepository, times(1)).selectPostsById(1L);
    }

    @Test
    void testSelectPostsById_PostNotExists() {
        when(jdbiPostRepository.selectPostsById(1L)).thenReturn(Optional.empty());

        PostNotFoundException exception = assertThrows(PostNotFoundException.class, () -> {
            postRepository.selectPostsById(1L);
        });

        assertEquals("Post not found with id: 1", exception.getMessage());
        verify(jdbiPostRepository, times(1)).selectPostsById(1L);
    }

    @Test
    void testSelectPostsByUserId() {
        List<Post> userPosts = new ArrayList<>();
        userPosts.add(samplePost);

        when(userRepository.selectByUserId(1L)).thenReturn(Optional.of(new User()));
        when(jdbiPostRepository.selectPostsByUserId(1L)).thenReturn(userPosts);

        List<Post> posts = postRepository.selectPostsByUserId(1L);

        assertEquals(userPosts, posts);
        verify(userRepository, times(1)).selectByUserId(1L);
        verify(jdbiPostRepository, times(1)).selectPostsByUserId(1L);
    }

    @Test
    void testDeletePost_PostExists() {
        when(jdbiPostRepository.selectPostsById(1L)).thenReturn(Optional.of(samplePost));

        postRepository.deletePost(1L);

        verify(likeRepository, times(1)).deleteAllLikesByPostId(1L);
        verify(commentRepository, times(1)).deleteAllCommentsByPostId(1L);
        verify(jdbiPostRepository, times(1)).deletePost(1L);
    }

    @Test
    void testDeletePost_PostNotExists() {
        when(jdbiPostRepository.selectPostsById(1L)).thenReturn(Optional.empty());

        postRepository.deletePost(1L);

        verify(likeRepository, never()).deleteAllLikesByPostId(1L);
        verify(commentRepository, never()).deleteAllCommentsByPostId(1L);
        verify(jdbiPostRepository, never()).deletePost(1L);
    }

    @Test
    void testDeletePostsByUserId() {
        List<Post> userPosts = new ArrayList<>();
        userPosts.add(samplePost);

        when(userRepository.selectByUserId(1L)).thenReturn(Optional.of(new User()));
        when(jdbiPostRepository.selectPostsByUserId(1L)).thenReturn(userPosts);
        when(jdbiPostRepository.selectPostsById(1L)).thenReturn(Optional.of(samplePost));

        doNothing().when(likeRepository).deleteAllLikesByPostId(1L);
        doNothing().when(commentRepository).deleteAllCommentsByPostId(1L);
        doNothing().when(jdbiPostRepository).deletePost(1L);

        postRepository.deletePostsByUserId(1L);

        verify(jdbiPostRepository, times(1)).selectPostsByUserId(1L);
        verify(jdbiPostRepository, times(1)).selectPostsById(1L);
        verify(likeRepository, times(1)).deleteAllLikesByPostId(1L);
        verify(commentRepository, times(1)).deleteAllCommentsByPostId(1L);
        verify(jdbiPostRepository, times(1)).deletePost(1L);
    }

    @Test
    void testFindAll() {
        List<Post> posts = new ArrayList<>();
        posts.add(samplePost);

        when(jdbiPostRepository.findAll(0, 10)).thenReturn(posts);

        List<Post> retrievedPosts = postRepository.findAll(0, 10);

        assertEquals(posts, retrievedPosts);
        verify(jdbiPostRepository, times(1)).findAll(0, 10);
    }

    @Test
    void testCountAll() {
        when(jdbiPostRepository.countAll()).thenReturn(10L);

        Long count = postRepository.countAll();

        assertEquals(10L, count);
        verify(jdbiPostRepository, times(1)).countAll();
    }

    @Test
    void testCountLikesByPostId() {
        when(jdbiPostRepository.countLikesByPostId(1L)).thenReturn(5);

        int likeCount = postRepository.countLikesByPostId(1L);

        assertEquals(5, likeCount);
        verify(jdbiPostRepository, times(1)).countLikesByPostId(1L);
    }

    @Test
    void testCountCommentsByPostId() {
        when(jdbiPostRepository.countCommentsByPostId(1L)).thenReturn(3);

        int commentCount = postRepository.countCommentsByPostId(1L);

        assertEquals(3, commentCount);
        verify(jdbiPostRepository, times(1)).countCommentsByPostId(1L);
    }
}
