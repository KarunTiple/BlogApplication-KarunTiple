package com.bikkadit.blogappapi.entities;

import com.bikkadit.blogappapi.entities.Post;
import lombok.Data;

import javax.persistence.*;

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
