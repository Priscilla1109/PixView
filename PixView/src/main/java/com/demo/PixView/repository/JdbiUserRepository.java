package com.demo.PixView.repository;

import com.demo.PixView.model.User;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;
import org.springframework.stereotype.Repository;
import org.jdbi.v3.sqlobject.locator.UseClasspathSqlLocator;

import java.util.Optional;


@Repository
@UseClasspathSqlLocator
public interface JdbiUserRepository {
    @SqlUpdate
    void insert(@BindBean User user);

    @SqlUpdate
    void update(@BindBean User user);

    @SqlQuery
    Optional<User> getUserById(@Bind("id") Long id);

    @SqlQuery
    void deleteUser(@Bind("id") Long id);
}
