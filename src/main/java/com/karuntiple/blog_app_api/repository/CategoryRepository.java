package com.karuntiple.blog_app_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.karuntiple.blog_app_api.entities.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
