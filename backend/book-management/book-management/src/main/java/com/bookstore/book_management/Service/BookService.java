/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.bookstore.book_management.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.bookstore.book_management.Entity.Book;
import com.bookstore.book_management.Repository.BookRepository;


@Service
public class BookService {

    private final BookRepository repository;

    public BookService(BookRepository repository) {
        this.repository = repository;
    }

    public List<Book> getAllBooks() {
        return repository.findAll();
    }

    public Book getBookById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public Book createBook(Book book) {
        return repository.save(book);
    }

    public Book updateBook(Long id, Book updatedBook) {
        return repository.findById(id).flatMap(book -> {
            book.setTitle(updatedBook.getTitle());
            book.setAuthor(updatedBook.getAuthor());
            book.setCategory(updatedBook.getCategory());
            book.setPrice(updatedBook.getPrice());
            return java.util.Optional.of(repository.save(book));
        }).orElse(null);
    }

    public boolean deleteBook(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return true;
        }
        return false;
    }

    public List<Book> findBooksbyCategoryId(Long categoryId) {
        return repository.findByCategoryId(categoryId);
    }

    public List<Book> findByTitleContainingIgnoreCase(String title) {
        return repository.findByTitleContainingIgnoreCase(title);
    }
}
