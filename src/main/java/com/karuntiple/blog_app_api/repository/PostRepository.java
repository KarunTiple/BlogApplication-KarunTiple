package com.karuntiple.blog_app_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.karuntiple.blog_app_api.entities.Category;
import com.karuntiple.blog_app_api.entities.Post;
import com.karuntiple.blog_app_api.entities.User;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {

    List<Post> findByUser(User user);

    List<Post> findByCategory(Category category);

    List<Post> findByTitleContaining(String title);


}
