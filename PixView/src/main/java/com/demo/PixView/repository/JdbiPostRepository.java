package com.demo.PixView.repository;

import com.demo.PixView.mapper.PostRowMapper;
import com.demo.PixView.model.Post;
import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.locator.UseClasspathSqlLocator;
import org.jdbi.v3.sqlobject.statement.GetGeneratedKeys;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@UseClasspathSqlLocator
@RegisterBeanMapper(PostRowMapper.class)
public interface JdbiPostRepository {
    @SqlUpdate
    @GetGeneratedKeys
    long createNewPost(@BindBean Post post);

    @SqlQuery
    Optional<Post> selectPostsById(@Bind("postId") Long postId);

    @SqlUpdate
    void deletePost(@Bind("postId") Long postId);

    @SqlQuery
    boolean existsById(@Bind("postId") Long postId);

    @SqlQuery
    List<Post> findAll(@Bind("offSet") int offSet, @Bind("limit") int limit);

    @SqlQuery
    Long countAll();

    @SqlQuery
    List<Post> selectPostsByUserId(@Bind("userId") Long userId);

    @SqlQuery
    int countLikesByPostId(@Bind("postId") Long postId);

    @SqlQuery
    int countCommentsByPostId(@Bind("postId")Long postId);
}
