package com.demo.PixView.service;

import com.demo.PixView.model.*;
import com.demo.PixView.repository.JdbiCommentRepository;
import com.demo.PixView.repository.JdbiLikeRepository;
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

    @Autowired
    private JdbiLikeRepository likeRepository;

    @Autowired
    private JdbiCommentRepository commentRepository;

    public Post createNewPost(String userName, String content){
        Optional<User> userOptional = userRepository.selectByUserName(userName);

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
        Optional<Post> post = postRepository.selectPostsById(postId);
        post.ifPresent(this::populateLikesAndComments);
        return post;
    }

    public void deletePostById(Long postId) {
        postRepository.selectPostsById(postId);
        postRepository.deletePost(postId);
    }

    public PostPageResponse<Post> listAllPosts(int page, int pageSize) {
        int offSet = page * pageSize;

        List<Post> posts = postRepository.findAll(offSet, pageSize);
        Long totalElements = postRepository.countAll();
        int totalPages = (int) Math.ceil((double) totalElements / pageSize);
        posts.forEach(this::populateLikesAndComments);

        return new PostPageResponse(posts,
                new Meta(page, pageSize, totalPages, totalElements));
    }

    public List<Post> getPostsByUserId(Long userId) {
        List<Post> posts = postRepository.selectPostsByUserId(userId);
        posts.forEach(this::populateLikesAndComments);
        return posts;
    }

    private void populateLikesAndComments(Post post) {
        int totalLikes = postRepository.countLikesByPostId(post.getPostId());
        int totalComments = postRepository.countCommentsByPostId(post.getPostId());
        post.setTotalLikes(totalLikes);
        post.setTotalComments(totalComments);
    }
}
