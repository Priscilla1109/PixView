package com.demo.PixView.service;

import com.demo.PixView.model.Post;
import com.demo.PixView.model.PostPageResponse;
import com.demo.PixView.model.User;
import com.demo.PixView.repository.PostRepository;
import com.demo.PixView.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PostServiceTest {
    @Mock
    private PostRepository postRepository;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private PostService postService;

    @Test
    void testCreateNewPost_Successful() {
        String userName = "user1";
        String content = "Post content";

        User user = new User();
        user.setUserId(1L);
        user.setUserName(userName);
        when(userRepository.selectByUserName(userName)).thenReturn(Optional.of(user));

        Post newPost = new Post();
        newPost.setUserId(user.getUserId());
        newPost.setUserName(user.getUserName());
        newPost.setContent(content);
        newPost.setLocalDateTime(LocalDateTime.now());
        newPost.setPostId(1L);
        when(postRepository.createNewPost(any())).thenReturn(1L);

        Post createdPost = postService.createNewPost(userName, content);

        assertEquals(newPost.getUserId(), createdPost.getUserId());
        assertEquals(newPost.getUserName(), createdPost.getUserName());
        assertEquals(newPost.getContent(), createdPost.getContent());
        assertEquals(newPost.getPostId(), createdPost.getPostId());
        verify(userRepository, times(1)).selectByUserName(userName);
        verify(postRepository, times(1)).createNewPost(any());
    }

    @Test
    void testGetPostsById_PostExists() {
        Long postId = 1L;

        Post post = new Post();
        post.setPostId(postId);
        post.setContent("Post content");
        when(postRepository.selectPostsById(postId)).thenReturn(Optional.of(post));

        Optional<Post> retrievedPost = postService.getPostsById(postId);

        assertEquals(post, retrievedPost.orElse(null));
        verify(postRepository, times(1)).selectPostsById(postId);
        verify(postRepository, times(1)).countLikesByPostId(postId);
        verify(postRepository, times(1)).countCommentsByPostId(postId);
    }

    @Test
    void testGetPostsById_PostNotExists() {
        Long postId = 1L;

        when(postRepository.selectPostsById(postId)).thenReturn(Optional.empty());

        Optional<Post> retrievedPost = postService.getPostsById(postId);

        assertEquals(Optional.empty(), retrievedPost);
        verify(postRepository, times(1)).selectPostsById(postId);
        verify(postRepository, never()).countLikesByPostId(anyLong());
        verify(postRepository, never()).countCommentsByPostId(anyLong());
    }

    @Test
    void testDeletePostById() {
        Long postId = 1L;

        postService.deletePostById(postId);

        verify(postRepository, times(1)).selectPostsById(postId);
        verify(postRepository, times(1)).deletePost(postId);
    }

    @Test
    void testListAllPosts() {
        int page = 0;
        int pageSize = 10;
        int totalPosts = 20;

        List<Post> posts = new ArrayList<>();
        for (int i = 1; i <= totalPosts; i++) {
            Post post = new Post();
            post.setPostId((long) i);
            post.setContent("Post content " + i);
            posts.add(post);
        }
        when(postRepository.findAll(anyInt(), anyInt())).thenReturn(posts);
        when(postRepository.countAll()).thenReturn((long) totalPosts);

        PostPageResponse<Post> postPageResponse = postService.listAllPosts(page, pageSize);

        assertEquals(posts, postPageResponse.getPosts());
        assertEquals(totalPosts, postPageResponse.getMeta().getTotalElements());
        assertEquals(2, postPageResponse.getMeta().getTotalPage());
        verify(postRepository, times(1)).findAll(page * pageSize, pageSize);
        verify(postRepository, times(1)).countAll();
    }

    @Test
    void testGetPostsByUserId() {
        Long userId = 1L;

        List<Post> posts = new ArrayList<>();
        for (int i = 1; i <= 3; i++) {
            Post post = new Post();
            post.setPostId((long) i);
            post.setContent("Post content " + i);
            posts.add(post);
        }
        when(postRepository.selectPostsByUserId(userId)).thenReturn(posts);

        List<Post> userPosts = postService.getPostsByUserId(userId);

        assertEquals(posts, userPosts);
        verify(postRepository, times(1)).selectPostsByUserId(userId);
    }
}
