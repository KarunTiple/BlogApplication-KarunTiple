package com.karuntiple.blog_app_api.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.karuntiple.blog_app_api.config.AppConstants;
import com.karuntiple.blog_app_api.payloads.ApiResponse;
import com.karuntiple.blog_app_api.payloads.CommentDto;
import com.karuntiple.blog_app_api.service.CommentService;

@Slf4j
@RestController
@RequestMapping("/api")
public class CommentController {

	@Autowired
	private CommentService commentService;

	/**
	 * @author Karun
	 * @param commentDto
	 * @param postId
	 * @return
	 * @apiNote This api is for Creating the Comment
	 */

	@PostMapping("/post/{postId}/comments")
	public ResponseEntity<CommentDto> createComment(@RequestBody CommentDto commentDto, @PathVariable Long postId) {

		log.info("Entering the CommentController to Create Comment with Post ID: {} ", postId);

		CommentDto createComment = this.commentService.createComment(commentDto, postId);

		log.info("Returning from CommentController after Creating Comment with Post ID: {} ", postId);

		return new ResponseEntity<>(createComment, HttpStatus.CREATED);

	}

	/**
	 * @author Karun
	 * @param commentId
	 * @return
	 * @apiNote This api is for Deleting the Comment
	 */
	@DeleteMapping("/comments/{commentId}")
	public ResponseEntity<ApiResponse> deleteComment(@PathVariable Long commentId) {

		log.info("Entering the CommentController to Delete Comment with Comment ID: {} ", commentId);

		this.commentService.deleteComment(commentId);

		log.info("Returning from CommentController after Deleting Comment with Comment ID: {} ", commentId);

		return new ResponseEntity<ApiResponse>(new ApiResponse(AppConstants.COMMENT_DELETED, true), HttpStatus.OK);

	}
}
