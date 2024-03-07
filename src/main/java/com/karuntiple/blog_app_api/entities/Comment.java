package com.karuntiple.blog_app_api.entities;

import lombok.Data;

import javax.persistence.*;

import com.karuntiple.blog_app_api.entities.Post;

@Data
@Entity
@Table(name = "COMMENT")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String content;

    @ManyToOne
    private Post post;
}
