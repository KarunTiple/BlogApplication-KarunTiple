package com.bikkadit.blogappapi.payloads;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class CategoryDto {


    private Integer categoryId;

    @NotBlank(message = "CategoryTitle must not be Blank")
    private String categoryTitle;

    @NotBlank(message = "Category Description  must not be Blank")
    private String categoryDescription;

}
