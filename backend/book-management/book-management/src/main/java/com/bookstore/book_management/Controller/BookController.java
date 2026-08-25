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
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bookstore.book_management.Dto.ApiResponse;
import com.bookstore.book_management.Entity.Book;
import com.bookstore.book_management.Service.BookService;

@RestController
@RequestMapping("/api/books")
public class BookController {

    private final BookService service;

    public BookController(BookService service) {
        this.service = service;
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public ApiResponse<?> getAllBooks(Authentication authentication) {
        return service.getAllBooks();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public ApiResponse<?> getBookById(@PathVariable Long id) {
        return service.getBookById(id);
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ApiResponse<?> createBook(@RequestBody Book book) {
        return service.createBook(book);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ApiResponse<?> deleteBook(@PathVariable Long id) {
        return service.deleteBook(id);
    }

    @PostMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ApiResponse<?> updateBook(@PathVariable Long id, @RequestBody Book updatedBook) {
        return service.updateBook(id, updatedBook);
    }

    @GetMapping("/search")
    public ApiResponse<?> searchBooksByTitle(@RequestParam String title) {
        return service.findByTitleContainingIgnoreCase(title);
    }

    @GetMapping("/category/{categoryId}")
    public ApiResponse<?> getBooksByCategory(@PathVariable Long categoryId) {
        return service.findBooksbyCategoryId(categoryId);
    }
}