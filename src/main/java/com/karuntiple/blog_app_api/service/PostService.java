package com.karuntiple.blog_app_api.service;

import java.util.List;

import com.karuntiple.blog_app_api.payloads.PostDto;
import com.karuntiple.blog_app_api.payloads.PostResponse;

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