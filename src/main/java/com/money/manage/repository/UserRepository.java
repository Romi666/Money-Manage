package com.money.manage.repository;

import com.money.manage.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;

public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsUserByUsername(String username);

    User findByUsername(String username);

    @Transactional
    void deleteByUsername(String username);
}
