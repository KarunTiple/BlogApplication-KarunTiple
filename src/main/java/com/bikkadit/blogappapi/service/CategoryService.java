package com.bikkadit.blogappapi.service;

import com.bikkadit.blogappapi.payloads.CategoryDto;

import java.util.List;

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
