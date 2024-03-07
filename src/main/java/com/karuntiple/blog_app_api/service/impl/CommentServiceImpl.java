package com.karuntiple.blog_app_api.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.karuntiple.blog_app_api.entities.Comment;
import com.karuntiple.blog_app_api.entities.Post;
import com.karuntiple.blog_app_api.exception.ResourceNotFoundException;
import com.karuntiple.blog_app_api.payloads.CommentDto;
import com.karuntiple.blog_app_api.repository.CommentRepository;
import com.karuntiple.blog_app_api.repository.PostRepository;
import com.karuntiple.blog_app_api.service.CommentService;

@Slf4j
@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentRepository commentRepo;

    @Autowired
    private PostRepository postRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public CommentDto createComment(CommentDto commentDto, Long postId) {

        log.info("Entering the CommentService to Create the Comment with Post ID: {}",postId);

        Post post = this.postRepo.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("post", "post id", postId));

        Comment comment = this.modelMapper.map(commentDto, Comment.class);

        comment.setPost(post);

        Comment savedComment = this.commentRepo.save(comment);

        log.info("Returning from CommentService after Creating the Comment with Post ID: {}",postId);

        return this.modelMapper.map(savedComment, CommentDto.class);

    }

    @Override
    public void deleteComment(Long commentId) {

        log.info("Entering the CommentService to Delete the Comment with Comment ID: {}",commentId);

        Comment comment = this.commentRepo.findById(commentId)
                .orElseThrow(() -> new ResourceNotFoundException("comment", "comment id", commentId));

        log.info("Returning from CommentService after Deleting the Comment with Comment ID: {}",commentId);

        this.commentRepo.delete(comment);

    }

}
