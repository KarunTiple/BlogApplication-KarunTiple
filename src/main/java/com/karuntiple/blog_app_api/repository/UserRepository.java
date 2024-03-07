package com.karuntiple.blog_app_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.karuntiple.blog_app_api.entities.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

}
