package com.demo.PixView.service;

import com.demo.PixView.exception.UserNotFoundException;
import com.demo.PixView.model.Post;
import com.demo.PixView.model.User;
import com.demo.PixView.repository.PostRepository;
import com.demo.PixView.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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

    public Optional<Post> getPostsByUserId(Long userId) {
        return postRepository.selectPostsByUserId(userId);
    }

    public void deletePostById(Long postId) {
        Optional<Post> postOptional = postRepository.selectPostsByUserId(postId);

        if (postOptional.isPresent()) {
            Post post = postOptional.get();

            postRepository.deletePost(post.getPostId());
        }
    }
}
