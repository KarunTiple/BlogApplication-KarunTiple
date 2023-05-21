package com.bikkadit.blogappapi.service.impl;

import com.bikkadit.blogappapi.entities.Category;
import com.bikkadit.blogappapi.exception.ResourceNotFoundException;
import com.bikkadit.blogappapi.payloads.CategoryDto;
import com.bikkadit.blogappapi.repository.CategoryRepository;
import com.bikkadit.blogappapi.service.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public CategoryDto createCategory(CategoryDto categoryDto) {

        log.info("Entering the CategoryService to Create the Category : {}");

        Category cat = this.modelMapper.map(categoryDto, Category.class);
        Category addedCat = this.categoryRepo.save(cat);

        log.info("Returning from CategoryService after Creating the Category : {}");

        return this.modelMapper.map(addedCat, CategoryDto.class);
    }

    @Override
    public CategoryDto updateCategory(CategoryDto categoryDto, Long categoryId) {

        log.info("Entering the CategoryService to Update the Category : {}");

        Category cat = this.categoryRepo.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category", "Category Id", categoryId));

        cat.setCategoryTitle(categoryDto.getCategoryTitle());
        cat.setCategoryDescription(categoryDto.getCategoryDescription());
        Category updatedCat = this.categoryRepo.save(cat);

        log.info("Returning from CategoryService after Updating the Category : {}");

        return this.modelMapper.map(updatedCat, CategoryDto.class);

    }

    @Override
    public void deleteCategory(Long categoryId) {

        log.info("Entering the CategoryService to Delete the Category : {}");

        Category cat = this.categoryRepo.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category", "category id", categoryId));

        log.info("Returning from CategoryService after Deleting the Category : {}");

        this.categoryRepo.delete(cat);

    }

    @Override
    public CategoryDto getCategory(Long categoryId) {

        log.info("Entering the CategoryService to Get Category : {}");

        Category cat = this.categoryRepo.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category", "category id", categoryId));

        log.info("Returning from CategoryService after Getting Category : {}");

        return this.modelMapper.map(cat, CategoryDto.class);

    }

    @Override
    public List<CategoryDto> getCategories() {

        log.info("Entering the CategoryService to Get All Category : {}");

        List<Category> categories = this.categoryRepo.findAll();

        List<CategoryDto> catDtos = categories.stream().map((cat) -> this.modelMapper.map(cat, CategoryDto.class))
                .collect(Collectors.toList());

        log.info("Returning from CategoryService after Getting All Category : {}");

        return catDtos;
    }

}
