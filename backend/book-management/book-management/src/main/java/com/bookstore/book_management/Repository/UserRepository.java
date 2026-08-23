/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.bookstore.book_management.Repository;


/**
 *
 * @author Admin
 */
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bookstore.book_management.Entity.User;


public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findByUsername(String username);

    User findByEmail(String email);
    boolean existsById(Long id);
};
