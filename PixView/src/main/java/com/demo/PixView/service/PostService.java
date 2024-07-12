package com.demo.PixView.service;

import com.demo.PixView.exception.PostNotFoundException;
import com.demo.PixView.exception.UserNotFoundException;
import com.demo.PixView.model.*;
import com.demo.PixView.repository.PostRepository;
import com.demo.PixView.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class PostService {
    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserRepository userRepository;

    public Post createNewPost(String userName, String content){
        Optional<User> userOptional = userRepository.selectByUserName(userName);

        if (!userOptional.isPresent()) {
            throw new UserNotFoundException("User does not existe: " + userName);
        }

        User user = userOptional.get();
        Post post = new Post();
        post.setUserId(user.getUserId());
        post.setUserName(user.getUserName());
        post.setContent(content);
        post.setLocalDateTime(LocalDateTime.now());

        Long generatedId = postRepository.createNewPost(post);
        post.setPostId(generatedId);

        return post;
    }

    public Optional<Post> getPostsById(Long postId) {
        if (!postRepository.existsById(postId)) {
            throw new PostNotFoundException("Post not found with id: " + postId);
        }
        return postRepository.selectPostsById(postId);
    }

    public void deletePostById(Long postId) {
        Optional<Post> postOptional = postRepository.selectPostsById(postId);

        if (postOptional.isPresent()) {
            Post post = postOptional.get();

            postRepository.deletePost(post.getPostId());
        }
    }

    public PostPageResponse<Post> listAllPosts(int page, int pageSize) {
        int offSet = page * pageSize;

        List<Post> posts = postRepository.findAll(offSet, pageSize);
        Long totalElements = postRepository.countAll();
        int totalPages = (int) Math.ceil((double) totalElements / pageSize);

        return new PostPageResponse(posts,
                new Meta(page, pageSize, totalPages, totalElements));
    }
}
