package com.demo.PixView.repository;

import com.demo.PixView.exception.PostNotFoundException;
import com.demo.PixView.exception.UserNotFoundException;
import com.demo.PixView.model.Post;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class PostRepository {
    private final JdbiPostRepository jdbiPostRepository;
    private final UserRepository userRepository;

    public Long createNewPost(final Post post) {
        return jdbiPostRepository.createNewPost(post);
    }

    public Optional<Post> selectPostsById(Long postId) {
        Optional<Post> optionalPost = jdbiPostRepository.selectPostsById(postId);
        return Optional.ofNullable(optionalPost.orElseThrow(() -> new PostNotFoundException("Post not found with id: " + postId)));
    }

    public List<Post> selectPostsByUserId(Long userId) {
        userRepository.selectByUserId(userId);
        return jdbiPostRepository.selectPostsByUserId(userId);
    }

    public void deletePost(Long postId) {
        selectPostsById(postId);
        jdbiPostRepository.deletePost(postId);
    }

    public List<Post> findAll(int offSet, int pageSize) {
        return jdbiPostRepository.findAll(offSet, pageSize);
    }

    public Long countAll() {
        return jdbiPostRepository.countAll();
    }

    public int countLikesByPostId(Long postId) {
        return jdbiPostRepository.countLikesByPostId(postId);
    }

    public int countCommentsByPostId(Long postId) {
        return jdbiPostRepository.countCommentsByPostId(postId);
    }
}
