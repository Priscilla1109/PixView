package com.demo.PixView.repository;

import com.demo.PixView.model.Like;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.locator.UseClasspathSqlLocator;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;
import org.springframework.stereotype.Repository;

@Repository
@UseClasspathSqlLocator
public interface JdbiLikeRepository {
    @SqlUpdate
    Long addLike(@Bind("postId") Long postId, @Bind("userId") Long userId);

    @SqlQuery
    boolean likeExists(@Bind("postId") Long postId, @Bind("userId") Long userId);
}
