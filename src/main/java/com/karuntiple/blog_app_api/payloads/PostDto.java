package com.karuntiple.blog_app_api.payloads;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class PostDto {

    private Integer postId;

    @NotBlank
    @Size(max = 50, message = "Max Character should be 50")
    private String title;

    @NotBlank
    @Size(max = 1000, message = "max Character should be 1000")
    private String content;

    private String imageName;

    private String addedDate;

    private CategoryDto category;

    private UserDto user;


}

