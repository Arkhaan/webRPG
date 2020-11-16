package com.greenfox.sideproject.repositories;

import com.greenfox.sideproject.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query("select u from User u where u.login=:login and u.password=:password")
    Optional<User> findByLoginAndPassword(@Param("login") String login, @Param("password") String password);

    @Query("select u from User u where u.apiKey=:apikey")
    Optional<User> findByApiKey(@Param("apikey") String apikey);
}
