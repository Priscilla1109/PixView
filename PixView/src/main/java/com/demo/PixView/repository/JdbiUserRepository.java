package com.demo.PixView.repository;

import com.demo.PixView.model.User;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.GetGeneratedKeys;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;
import org.jdbi.v3.sqlobject.locator.UseClasspathSqlLocator;

import org.springframework.stereotype.Repository;


@Repository
@UseClasspathSqlLocator
public interface JdbiUserRepository {
    @SqlUpdate
    @GetGeneratedKeys
    long createNewUser(@BindBean User user);

    @SqlQuery
    int selectByUserName(@Bind("userName") String userName);

//    @SqlUpdate
//    void update(@BindBean User user);
//
//    @SqlQuery
//    Optional<User> getUserById(@Bind("id") Long id);
//
//    @SqlQuery
//    void deleteUser(@Bind("id") Long id);
}
