/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.bookstore.book_management.Service;

/**
 *
 * @author Admin
 */
import org.springframework.stereotype.Service;
import com.bookstore.book_management.Repository.CategoryRepository;
import com.bookstore.book_management.Entity.Category;
import com.bookstore.book_management.Dto.ApiResponse;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryService(
            CategoryRepository categoryRepository
    ) {
        this.categoryRepository = categoryRepository;
    }

    public ApiResponse<?> createCategory(
            Category category
    ) {

        try {

            Category saved =
                    categoryRepository.save(category);

            return ApiResponse.created(
                    saved,
                    "Category created successfully"
            );

        } catch (Exception e) {

            return ApiResponse.internalServerError();
        }
    }

    public ApiResponse<?> getCategoryById(
            Long id
    ) {

        Category category =
                categoryRepository.findById(id)
                        .orElse(null);

        if (category == null) {

            return ApiResponse.notFound(
                    "Category not found"
            );
        }

        return ApiResponse.ok(category);
    }

    public ApiResponse<?> updateCategory(
            Long id,
            Category updatedCategory
    ) {

        Category existingCategory =
                categoryRepository.findById(id)
                        .orElse(null);

        if (existingCategory == null) {

            return ApiResponse.notFound(
                    "Category not found"
            );
        }

        try {

            existingCategory.setName(
                    updatedCategory.getName()
            );

            Category saved =
                    categoryRepository.save(
                            existingCategory
                    );

            return ApiResponse.ok(
                    saved,
                    "Category updated successfully"
            );

        } catch (Exception e) {

            return ApiResponse.internalServerError();
        }
    }
    
    @Transactional
    public ApiResponse<?> deleteCategory(
            Long id
    ) {

        Category category =
                categoryRepository.findById(id)
                        .orElse(null);

        if (category == null) {

            return ApiResponse.notFound(
                    "Category not found"
            );
        }

        try {

            categoryRepository.delete(category);

            return ApiResponse.noContent();

        } catch (Exception e) {

            return ApiResponse.internalServerError();
        }
    }

    public ApiResponse<?> getAllCategories() {

        return ApiResponse.ok(
                categoryRepository.findAll()
        );
    }
}
