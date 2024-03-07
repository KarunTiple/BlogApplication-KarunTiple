package com.karuntiple.blog_app_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.karuntiple.blog_app_api.entities.Role;

public interface RoleRepository extends JpaRepository<Role, Integer> {
}
