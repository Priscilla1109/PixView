package com.demo.PixView.repository;

import com.demo.PixView.model.Comment;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.locator.UseClasspathSqlLocator;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@UseClasspathSqlLocator
public interface JdbiCommentRepository {
    @SqlUpdate
    void addComment(@BindBean Comment comment);

    @SqlQuery
    Optional<Comment> selectByCommentId(@Bind("commentId") Long commentId);

    @SqlUpdate
    void deleteCommentById(@Bind("commentId") Long commentId);
}
