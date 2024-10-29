package com.uncode.rest_api_security.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.uncode.rest_api_security.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}