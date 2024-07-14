package com.demo.PixView.repository;

import com.demo.PixView.model.Post;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class PostRepository {
    private final JdbiPostRepository jdbiPostRepository;

    public Long createNewPost(final Post post) {
        return jdbiPostRepository.createNewPost(post);
    }

    public Optional<Post> selectPostsById(Long postId) {
        return jdbiPostRepository.selectPostsById(postId);
    }

    public List<Post> selectPostsByUserId(Long userId) {
        return jdbiPostRepository.selectPostsByUserId(userId);
    }

    public void deletePost(Long postId) {
        jdbiPostRepository.deletePost(postId);
    }

    public boolean existsById(Long postId) {
        return jdbiPostRepository.existsById(postId);
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
}
