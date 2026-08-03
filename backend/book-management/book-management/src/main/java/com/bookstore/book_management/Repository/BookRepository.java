/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.bookstore.book_management.Repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bookstore.book_management.Entity.Book;

/**
 *
 * @author Admin
 */
public interface BookRepository
    extends JpaRepository<Book, Long> {
        List<Book> findByCategoryId(Long categoryId);

        List<Book> findByTitleContainingIgnoreCase(String title);
    }
