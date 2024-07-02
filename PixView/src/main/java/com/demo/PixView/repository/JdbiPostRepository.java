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

@Repository
@UseClasspathSqlLocator
@RegisterBeanMapper(PostRowMapper.class)
public interface JdbiPostRepository {
    @SqlUpdate
    @GetGeneratedKeys
    long createNewPost(@BindBean Post post);

    @SqlQuery
    List<Post> selectPostsByUserId(@Bind("userId") Long userId);

}
