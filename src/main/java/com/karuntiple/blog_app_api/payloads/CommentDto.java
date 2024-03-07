package com.karuntiple.blog_app_api.payloads;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class CommentDto {

    private Long id;

    @NotEmpty
    private String content;


}
