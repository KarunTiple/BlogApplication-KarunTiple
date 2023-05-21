package com.bikkadit.blogappapi.service.impl;

import com.bikkadit.blogappapi.entities.Category;
import com.bikkadit.blogappapi.entities.Post;
import com.bikkadit.blogappapi.entities.User;
import com.bikkadit.blogappapi.exception.ResourceNotFoundException;
import com.bikkadit.blogappapi.payloads.PostDto;
import com.bikkadit.blogappapi.payloads.PostResponse;
import com.bikkadit.blogappapi.repository.CategoryRepository;
import com.bikkadit.blogappapi.repository.PostRepository;
import com.bikkadit.blogappapi.repository.UserRepository;
import com.bikkadit.blogappapi.service.PostService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepository postRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private CategoryRepository categoryRepo;

    @Override
    public PostDto createPost(PostDto postDto, Long userId, Long categoryId) {

        log.info("Entering the PostService to Create the Post : {}");

        User user = this.userRepo.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "user id", userId));

        Category category = this.categoryRepo.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category", "category id", categoryId));

        Post post = this.modelMapper.map(postDto, Post.class);

        post.setImageName("default.png");
        post.setAddedDate(new Date());
        post.setUser(user);
        post.setCategory(category);

        Post newPost = this.postRepo.save(post);

        log.info("Returning from PostService after Creating the Post : {}");

        return this.modelMapper.map(newPost, PostDto.class);
    }

    @Override
    public PostDto updatePost(PostDto postDto, Long postId) {

        log.info("Entering the PostService to Update the Post with Post ID: {}",postId);

        Post post = this.postRepo.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("Post", "post id", postId));

        post.setTitle(postDto.getTitle());
        post.setContent(postDto.getContent());
        post.setImageName(postDto.getImageName());

        Post updatedPost = this.postRepo.save(post);

        log.info("Returning from PostService after Updating the Post with Post ID: {}",postId);

        return this.modelMapper.map(updatedPost, PostDto.class);

    }

    @Override
    public void deletePost(Long postId) {

        log.info("Entering the PostService to Delete the Post with Post ID: {}",postId);

        Post post = this.postRepo.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("Post", "post id", postId));

        log.info("Returning from PostService after Deleting the Post with Post ID: {}",postId);

        this.postRepo.delete(post);

    }

    @Override
    public PostResponse getAllPost(Integer pageNumber, Integer pageSize, String sortBy, String sortDir) {

        log.info("Entering the PostService to Get All Post : {}");

        Sort sort = null;
        if (sortDir.equalsIgnoreCase("asc")) {
            sort = Sort.by(sortBy).ascending();
        } else {
            sort = Sort.by(sortBy).descending();
        }

        Pageable p = PageRequest.of(pageNumber, pageSize, sort);

        Page<Post> pagePost = this.postRepo.findAll(p);

        List<Post> allPosts = pagePost.getContent();

        List<PostDto> postDtos = allPosts.stream().map((post) -> this.modelMapper.map(post, PostDto.class))
                .collect(Collectors.toList());

        PostResponse postResponse = new PostResponse();
        postResponse.setContent(postDtos);
        postResponse.setPageNumber(pagePost.getNumber());
        postResponse.setPageSize(pagePost.getSize());
        postResponse.setTotalElements(pagePost.getTotalElements());
        postResponse.setTotalPages(pagePost.getTotalPages());
        postResponse.setLastPage(pagePost.isLast());

        log.info("Returning from PostService after Getting All Post : {}");

        return postResponse;
    }

    @Override
    public PostDto getPostById(Long postId) {

        log.info("Entering the PostService to Get Post with Post ID: {}",postId);

        Post post = this.postRepo.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("Post", "post id", postId));

        log.info("Returning from PostService after Getting Post with Post ID: {}",postId);


        return this.modelMapper.map(post, PostDto.class);
    }

    @Override
    public List<PostDto> getPostsByCategory(Long categoryId) {

        log.info("Entering the PostService to Get Post by Category with Category ID: {}",categoryId);

        Category cat = this.categoryRepo.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category", "category id", categoryId));

        List<Post> posts = this.postRepo.findByCategory(cat);

        List<PostDto> postDtos = posts.stream().map(post -> this.modelMapper.map(post, PostDto.class))
                .collect(Collectors.toList());

        log.info("Returning from PostService after Getting Post by Category with Category ID: {}",categoryId);

        return postDtos;
    }

    @Override
    public List<PostDto> getPostsByUser(Long userId) {

        log.info("Entering the PostService to Get Post by User with User ID: {}", userId);

        User user = this.userRepo.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "user id", userId));

        List<Post> posts = this.postRepo.findByUser(user);

        List<PostDto> postDtos = posts.stream().map(post -> this.modelMapper.map(post, PostDto.class))
                .collect(Collectors.toList());

        log.info("Returning from PostService after Getting Post by User with User ID: {}", userId);

        return postDtos;
    }

    @Override
    public List<PostDto> searchPosts(String keyword) {

        log.info("Entering the PostService to Search Post: {}");

        List<Post> posts = this.postRepo.findByTitleContaining(keyword);
        List<PostDto> postDtos = posts.stream().map((post) -> this.modelMapper.map(post, PostDto.class))
                .collect(Collectors.toList());

        log.info("Returning from PostService after Searching Post : {}");

        return postDtos;
    }

}
