package com.demo.PixView.repository;

import com.demo.PixView.mapper.UserRowMapper;
import com.demo.PixView.model.User;
import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.GetGeneratedKeys;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;
import org.jdbi.v3.sqlobject.locator.UseClasspathSqlLocator;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
@UseClasspathSqlLocator
@RegisterBeanMapper(UserRowMapper.class)
public interface JdbiUserRepository {
    @SqlUpdate
    @GetGeneratedKeys
    long createNewUser(@BindBean User user);

    @SqlQuery
    Optional<User> selectByUserName(@Bind("userName") String userName);

    @SqlQuery
    Optional<User> selectUserById(@Bind("userId") Long userId);

    @SqlQuery
    Optional<User> selectByEmail(@Bind("email") String email);

    @SqlUpdate
    void deleteUser(@Bind("userId") Long userId);

    @SqlQuery
    List<User> findAll(@Bind("offSet") int offSet, @Bind("limit") int limit);

    @SqlQuery
    Long countAll();
}
