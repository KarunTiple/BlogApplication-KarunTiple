package com.bikkadit.blogappapi.controller;

import com.bikkadit.blogappapi.config.AppConstants;
import com.bikkadit.blogappapi.payloads.ApiResponse;
import com.bikkadit.blogappapi.payloads.CategoryDto;
import com.bikkadit.blogappapi.service.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

//	create

    /**
     * @author Karun
     * @param categoryDto
     * @return
     * @apiNote This api is for Creating the Category
     */

    @PostMapping("/create")
    public ResponseEntity<CategoryDto> createCategory(@Valid @RequestBody CategoryDto categoryDto) {

        log.info("Entering the CategoryController to Create Category : {} ");

        CategoryDto createCategory = this.categoryService.createCategory(categoryDto);

        log.info("Returning from CategoryController after Creating Category : {} ");

        return new ResponseEntity<CategoryDto>(createCategory, HttpStatus.CREATED);
    }

//	update

    /**
     * @author Karun
     * @param categoryDto
     * @param catId
     * @return
     * @apiNote This api is for Updating the Category
     */

    @PutMapping("/{catId}")
    public ResponseEntity<CategoryDto> updateCategory(@Valid @RequestBody CategoryDto categoryDto,
                                                      @PathVariable Long catId) {

        log.info("Entering the CategoryController to Update Category with ID: {} ",catId);

        CategoryDto updatedCategory = this.categoryService.updateCategory(categoryDto, catId);

        log.info("Returning from CategoryController after Updating Category with ID: {} ",catId);

        return new ResponseEntity<CategoryDto>(updatedCategory, HttpStatus.OK);
    }

//	delete

    /**
     * @author Karun
     * @param catId
     * @return
     * @apiNote This api is for Deleting the Category
     */
    @DeleteMapping("/{catId}")
    public ResponseEntity<ApiResponse> deleteCategory(@PathVariable Long catId) {

        log.info("Entering the CategoryController to Delete Category with ID: {} ",catId);

        this.categoryService.deleteCategory(catId);

        log.info("Returning from CategoryController after Deleting Category with ID: {} ",catId);

        return new ResponseEntity<ApiResponse>(new ApiResponse(AppConstants.CATEGORY_DELETED, true),
                HttpStatus.OK);

    }
//	get

    /**
     * @author Karun
     * @param catId
     * @return
     * @apiNote This api is for Getting the Category
     */

    @GetMapping("/{catId}")
    public ResponseEntity<CategoryDto> getCategory(@PathVariable Long catId) {

        log.info("Entering the CategoryController to Get Category with ID: {} ",catId);

        CategoryDto categoryDto = this.categoryService.getCategory(catId);

        log.info("Returning from CategoryController after Getting Category with ID: {} ",catId);

        return new ResponseEntity<CategoryDto>(categoryDto, HttpStatus.OK);

    }

//	getall

    /**
     * @author Karun
     * @return
     * @apiNote This api is for Getting All Category
     */

    @GetMapping("/")
    public ResponseEntity<List<CategoryDto>> getCategories() {

        log.info("Entering the CategoryController to Get All Category : {} ");

        List<CategoryDto> categories = this.categoryService.getCategories();

        log.info("Returning from CategoryController after Getting All Category : {} ");

        return ResponseEntity.ok(categories);
    }
}
