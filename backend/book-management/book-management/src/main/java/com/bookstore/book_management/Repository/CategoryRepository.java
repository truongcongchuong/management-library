/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.bookstore.book_management.Repository;

/**
 *
 * @author Admin
 */
import org.springframework.data.jpa.repository.JpaRepository;

import com.bookstore.book_management.Entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {};
