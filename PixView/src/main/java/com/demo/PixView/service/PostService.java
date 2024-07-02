package com.demo.PixView.service;

import com.demo.PixView.model.Post;
import com.demo.PixView.repository.JdbiPostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PostService {
    @Autowired
    private JdbiPostRepository postRepository;

    public void createNewPost(Post post){
        post.setLocalDateTime(LocalDateTime.now());
        Long generatedId = postRepository.createNewPost(post);
        post.setPostId(generatedId);
    }

    public List<Post> getPostsByUserId(Long userId) {
        return postRepository.selectPostsByUserId(userId);
    }
}
