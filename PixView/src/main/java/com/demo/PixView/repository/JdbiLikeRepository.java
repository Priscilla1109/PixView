package com.demo.PixView.repository;

import com.demo.PixView.model.Like;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.locator.UseClasspathSqlLocator;
import org.jdbi.v3.sqlobject.statement.GetGeneratedKeys;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@UseClasspathSqlLocator
public interface JdbiLikeRepository {
    @SqlUpdate
    @GetGeneratedKeys
    long addLike(@BindBean Like like);
    @SqlQuery
    boolean likeExists(@BindBean Like like);

    @SqlQuery
    Optional<Like> selectByLikeId(@Bind("likeId") Long likeId);

    @SqlUpdate
    void deleteLikeById(@Bind("likeId") Long likeId);

    @SqlUpdate
    void deleteAllLikesByPostId(@Bind("postId") Long postId);
}
