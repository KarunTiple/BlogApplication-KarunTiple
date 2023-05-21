package com.bikkadit.blogappapi.repository;

import com.bikkadit.blogappapi.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Integer> {
}
