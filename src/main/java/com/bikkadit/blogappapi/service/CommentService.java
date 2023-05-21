package com.bikkadit.blogappapi.service;

import com.bikkadit.blogappapi.payloads.CommentDto;

public interface CommentService {

    CommentDto createComment(CommentDto commentDto, Long postId);

    void deleteComment(Long commentId);
}
