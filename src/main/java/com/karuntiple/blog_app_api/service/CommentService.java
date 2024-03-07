package com.karuntiple.blog_app_api.service;

import com.karuntiple.blog_app_api.payloads.CommentDto;

public interface CommentService {

    CommentDto createComment(CommentDto commentDto, Long postId);

    void deleteComment(Long commentId);
}
