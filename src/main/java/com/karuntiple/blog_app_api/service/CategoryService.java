package com.karuntiple.blog_app_api.service;

import java.util.List;

import com.karuntiple.blog_app_api.payloads.CategoryDto;

public interface CategoryService {

    // create
    CategoryDto createCategory(CategoryDto categoryDto);

    // update
    CategoryDto updateCategory(CategoryDto categoryDto, Long categoryId);

    // delete
    void deleteCategory(Long categoryId);

    // get
    CategoryDto getCategory(Long categoryId);

    // getAll
    List<CategoryDto> getCategories();
}
