/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.bookstore.book_management.Controller;

/**
 *
 * @author Admin
 */

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bookstore.book_management.Dto.ApiResponse;
import com.bookstore.book_management.Entity.Category;
import com.bookstore.book_management.Service.CategoryService;


@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    private final CategoryService service;

    public CategoryController(CategoryService service) {
        this.service = service;
    }

    @GetMapping
    public ApiResponse<?> getAllCategories() {
        return service.getAllCategories();
    }

    @GetMapping("/{id}")
    public ApiResponse<?> getCategoryById(@PathVariable Long id) {
        return service.getCategoryById(id);
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ApiResponse<?> createCategory(@RequestBody Category category) {
        return service.createCategory(category);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ApiResponse<?> deleteCategory(@PathVariable Long id) {
        return service.deleteCategory(id);
    }

    @PostMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ApiResponse<?> updateCategory(@PathVariable Long id, @RequestBody Category updatedCategory) {
        return service.updateCategory(id, updatedCategory);
    }
}
