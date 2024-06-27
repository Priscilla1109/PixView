package com.demo.PixView.repository;

import com.demo.PixView.model.User;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;
import org.jdbi.v3.sqlobject.locator.UseClasspathSqlLocator;

import org.springframework.stereotype.Repository;


@Repository
@UseClasspathSqlLocator
public interface JdbiUserRepository {
    @SqlUpdate
    void createNewUser(@BindBean User user);

//    @SqlUpdate
//    void update(@BindBean User user);
//
//    @SqlQuery
//    Optional<User> getUserById(@Bind("id") Long id);
//
//    @SqlQuery
//    void deleteUser(@Bind("id") Long id);
}
