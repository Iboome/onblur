package com.example.repository;

import com.example.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
public interface UserRepository extends Repository<User,String> {

    Page<User> findAll(Pageable pageable);

    User save(User user);

    User findUserById(String id);

    void deleteUserById(String id);

    @Query("select u from User u where u.nick = ?1")
    User findUser(@Param(value = "nick") String nick);
}
