package com.bikkadit.blogappapi.service;

import com.bikkadit.blogappapi.payloads.PostDto;
import com.bikkadit.blogappapi.payloads.PostResponse;

import java.util.List;

public interface PostService {


    //	create
    PostDto createPost(PostDto postDto, Long userId, Long categoryId);

    //	update
    PostDto updatePost(PostDto postDto, Long postId);

    //	delete
    void deletePost(Long postId);

    //	get all post
    PostResponse getAllPost(Integer pageNumber, Integer pageSize, String sortBy, String sortDir);

    //	get single post
    PostDto getPostById(Long postId);

    //	get all post by Category
    List<PostDto> getPostsByCategory(Long categoryId);

    //	get all post by User
    List<PostDto> getPostsByUser(Long userId);

    //	search post
    List<PostDto> searchPosts(String keyword);
}