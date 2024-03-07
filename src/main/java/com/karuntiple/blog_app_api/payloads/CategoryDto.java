package com.karuntiple.blog_app_api.payloads;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class CategoryDto {


    private Integer categoryId;

    @NotBlank(message = "CategoryTitle must not be Blank")
    private String categoryTitle;

    @NotBlank(message = "Category Description  must not be Blank")
    private String categoryDescription;

}
