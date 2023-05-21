package com.bikkadit.blogappapi.repository;

import com.bikkadit.blogappapi.entities.Category;
import com.bikkadit.blogappapi.entities.Post;
import com.bikkadit.blogappapi.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {

    List<Post> findByUser(User user);

    List<Post> findByCategory(Category category);

    List<Post> findByTitleContaining(String title);


}
