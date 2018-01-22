package com.iqmsoft.rest.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.iqmsoft.rest.entity.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findById(Long userId);
    Optional<User> findByLogin(String login);
}
