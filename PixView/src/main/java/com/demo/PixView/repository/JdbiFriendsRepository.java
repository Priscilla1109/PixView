package com.demo.PixView.repository;

import com.demo.PixView.model.User;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.locator.UseClasspathSqlLocator;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@UseClasspathSqlLocator
public interface JdbiFriendsRepository {
    @SqlUpdate
    void addNewFriend(@Bind("userId") Long userId, @Bind("friendId") Long friendId);

    @SqlUpdate
    void removeFriend(@Bind("userId") Long userId, @Bind("friendId") Long friendId);

    @SqlQuery
    List<User> findFriends(@Bind("userId") Long userId);

    @SqlQuery
    Optional<User> findById(@Bind("userId") Long userId);
}
