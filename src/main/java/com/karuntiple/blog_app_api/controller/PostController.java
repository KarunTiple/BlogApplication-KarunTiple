package com.karuntiple.blog_app_api.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.karuntiple.blog_app_api.config.AppConstants;
import com.karuntiple.blog_app_api.payloads.ApiResponse;
import com.karuntiple.blog_app_api.payloads.PostDto;
import com.karuntiple.blog_app_api.payloads.PostResponse;
import com.karuntiple.blog_app_api.service.FileService;
import com.karuntiple.blog_app_api.service.PostService;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api")
public class PostController {

    @Autowired
    private PostService postService;

    @Autowired
    private FileService fileService;

    @Value ("${project.image}")
    public String path;


//	create

    /**
     * @author Karun
     * @param postDto
     * @param userId
     * @param categoryId
     * @return
     * @apiNote This api is for Creating the Post
     */
    @PostMapping("/user/{userId}/category/{categoryId}/posts")
    public ResponseEntity<PostDto> createPost(@Valid @RequestBody PostDto postDto, @PathVariable Long userId,
                                              @PathVariable Long categoryId) {

        log.info("Entering the PostController to Create Post with User ID : {} ",userId," And in the Category with Category ID : {}",categoryId);

        PostDto createPost = this.postService.createPost(postDto, userId, categoryId);

        log.info("Returning from PostController after Creating Post with User ID : {} ",userId," And in the Category with Category ID : {}",categoryId);

        return new ResponseEntity<PostDto>(createPost, HttpStatus.CREATED);
    }

    // get by user

    /**
     * @author Karun
     * @param userId
     * @return
     * @apiNote This api is for Getting the Post by User
     */
    @GetMapping("/user/{userId}/posts")
    public ResponseEntity<List<PostDto>> getPostsByUser(@PathVariable Long userId) {

        log.info("Entering the PostController to Get Post by User with User ID: {} ",userId);

        List<PostDto> posts = this.postService.getPostsByUser(userId);

        log.info("Returning from PostController after Getting Post by User with User ID: {} ",userId);

        return new ResponseEntity<List<PostDto>>(posts, HttpStatus.OK);
    }

    // get by category

    /**
     * @author Karun
     * @param categoryId
     * @return
     * @apiNote This api is for Getting the Post by Category
     */

    @GetMapping("/category/{categoryId}/posts")
    public ResponseEntity<List<PostDto>> getPostsByCategory(@PathVariable Long categoryId) {

        log.info("Entering the PostController to Get Post by Category with Category ID {} :",categoryId);

        List<PostDto> posts = this.postService.getPostsByCategory(categoryId);

        log.info("Returning from PostController after Getting Post by Category with Category ID {} :",categoryId);

        return new ResponseEntity<List<PostDto>>(posts, HttpStatus.OK);
    }

    // get All Post

    /**
     * @author Karun
     * @param pageNumber
     * @param pageSize
     * @param sortBy
     * @param sortDir
     * @return
     * @apiNote This api is for Getting All Post
     */

    @GetMapping("/posts")
    public ResponseEntity<PostResponse> getAllPost(
            @RequestParam(value = "pageNumber", defaultValue = AppConstants.PAGE_NUMBER, required = false) Integer pageNumber,
            @RequestParam(value = "pageSize", defaultValue = AppConstants.PAGE_SIZE, required = false) Integer pageSize,
            @RequestParam(value = "sortBy", defaultValue = AppConstants.SORT_BY, required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = AppConstants.SORT_DIR, required = false) String sortDir) {

        log.info("Entering the PostController to Get All Post : {} ");

        PostResponse postResponse = this.postService.getAllPost(pageNumber, pageSize, sortBy, sortDir);

        log.info("Returning from PostController after Getting All Post : {} ");

        return new ResponseEntity<PostResponse>(postResponse, HttpStatus.OK);
    }

    // get Post by ID

    /**
     * @author Karun
     * @param postId
     * @return
     * @apiNote This api is for Getting Post by postId
     */

    @GetMapping("/posts/{postId}")
    public ResponseEntity<PostDto> getPostById(@PathVariable Long postId) {

        log.info("Entering the PostController to Get All Post with Post ID: {} ",postId);

        PostDto postDto = this.postService.getPostById(postId);

        log.info("Returning from PostController after Getting All Post with Post ID: {} ",postId);

        return new ResponseEntity<PostDto>(postDto, HttpStatus.OK);
    }

    // delete Post by Id

    /**
     * @author Karun
     * @param postId
     * @return
     * @apiNote This api is for Deleting the Post
     */
    @DeleteMapping("/posts/{postId}")
    public ApiResponse deletePost(@PathVariable Long postId) {

        log.info("Entering the PostController to Delete Post with Post ID: {} ",postId);

        this.postService.deletePost(postId);

        log.info("Returning from PostController after Deleting Post with Post ID: {} ",postId);

        return new ApiResponse(AppConstants.POST_DELETED, true);
    }

    // update Post

    /**
     * @author Karun
     * @param postDto
     * @param postId
     * @return
     * @apiNote This api is for Updating the Post
     */

    @PutMapping("/posts/{postId}")
    public ResponseEntity<PostDto> updatePost(@RequestBody PostDto postDto, @PathVariable Long postId) {

        log.info("Entering the PostController to Update Post with Post ID: {} ",postId);

        PostDto updatePost = this.postService.updatePost(postDto, postId);

        log.info("Returning from PostController after Updating Post with Post ID: {} ",postId);

        return new ResponseEntity<PostDto>(updatePost, HttpStatus.OK);
    }

    // search Post

    /**
     * @author Karun
     * @param keywords
     * @return
     * @apiNote This api is for Searching the Post
     */

    @GetMapping("/posts/search/{keywords}")
    public ResponseEntity<List<PostDto>> searchPostByTitle(@PathVariable("keywords") String keywords) {

        log.info("Entering the PostController to Search Post : {} ");

        List<PostDto> result = this.postService.searchPosts(keywords);

        log.info("Returning from PostController after Searching Post : {} ");

        return new ResponseEntity<List<PostDto>>(result, HttpStatus.FOUND);
    }

    // post image upload

    /**
     * @author Karun
     * @param image
     * @param postId
     * @return
     * @throws IOException
     */

    @PostMapping("/post/image/upload/{postId}")
    public ResponseEntity<PostDto> uploadPostImage(@RequestPart("image") MultipartFile image,
                                                   @PathVariable Long postId) throws IOException {

        log.info("Entering the PostController to Upload Image in the Post with Post ID: {} ",postId);

        PostDto postDto = this.postService.getPostById(postId);

        String fileImage = this.fileService.uploadImage(path, image);

        postDto.setImageName(fileImage);

        PostDto updatePost = this.postService.updatePost(postDto, postId);

        log.info("Returning from PostController after Uploading Image in the Post with Post ID: {} ",postId);

        return new ResponseEntity<PostDto>(updatePost, HttpStatus.OK);

    }

    // method to serve the files

    /**
     * @author Karun
     * @param imageName
     * @param response
     * @throws IOException
     */

    @GetMapping(value = "/post/image/{imageName}", produces = MediaType.IMAGE_JPEG_VALUE)
    public void downloadImage(@PathVariable("imageName") String imageName, HttpServletResponse response)
            throws IOException {

        log.info("Entering the PostController to Serve the Image on the Server : {}");

        InputStream resource = this.fileService.getResource(path, imageName);

        response.setContentType(MediaType.IMAGE_JPEG_VALUE);

        StreamUtils.copy(resource, response.getOutputStream());

        log.info("Returning from PostController after Serving the Image on the Server : {}");

    }

}

