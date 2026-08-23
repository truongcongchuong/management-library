/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.bookstore.book_management.Service;

import org.springframework.stereotype.Service;

import com.bookstore.book_management.Dto.ApiResponse;
import com.bookstore.book_management.Entity.Book;
import com.bookstore.book_management.Repository.BookRepository;


@Service
public class BookService {

    private final BookRepository repository;

    public BookService(BookRepository repository) {
        this.repository = repository;
    }

    public ApiResponse<?> getAllBooks() {
        try {
            return ApiResponse.ok(repository.findAll());
        } catch (Exception e) {
            return ApiResponse.internalServerError();
        }
        
    }

    public ApiResponse<?> getBookById(Long id) {

        try {
            Book book = repository.findById(id).orElse(null);

            if (book == null) {

                return ApiResponse.notFound("Book Not Found");
            }
            return ApiResponse.ok(book);
            
        } catch (Exception e) {
            return ApiResponse.internalServerError();
        }
        
    }

    public ApiResponse<?> createBook(Book book) {
        try {

            if (!repository.existsByIsbn(book.getIsbn())) {
                return ApiResponse.conflict("ISBN already exists");
            }

            repository.save(book);


            return ApiResponse.created(null);
        } catch (Exception e) {
            return ApiResponse.internalServerError();
        }
        
    }

    public ApiResponse<?> updateBook( Long id, Book updatedBook) {

        try {
            Book book =
                repository.findById(id)
                        .orElse(null);

            if (book == null) {

                return ApiResponse.notFound(
                        "Book not found"
                );
            }

            book.setTitle(updatedBook.getTitle());

            book.setAuthor(updatedBook.getAuthor());

            book.setCategory(updatedBook.getCategory());

            book.setPrice(updatedBook.getPrice());

            Book savedBook = repository.save(book);

            return ApiResponse.ok(savedBook,"Book updated successfully");
        } catch (Exception e) {
            return ApiResponse.internalServerError();
        }
        
    }

    public ApiResponse<?> deleteBook(Long id) {

        try { 
            if (repository.existsById(id)) {
                repository.deleteById(id);
                return ApiResponse.ok(null, "Delete Book Successfully");
            }
            
            return ApiResponse.conflict("This Book Don't Exist");

        } catch (Exception e) {
            return ApiResponse.internalServerError();
        }
       
    }

    public ApiResponse<?> findBooksbyCategoryId(Long categoryId) {
        try {

            return ApiResponse.ok(repository.findByCategoryId(categoryId));
        } catch (Exception e) {
            return ApiResponse.internalServerError();
        }
        
    }

    public ApiResponse<?> findByTitleContainingIgnoreCase(String title) {
         try {

            return ApiResponse.ok(repository.findByTitleContainingIgnoreCase(title));
        } catch (Exception e) {
            return ApiResponse.internalServerError();
        }
    }
}
