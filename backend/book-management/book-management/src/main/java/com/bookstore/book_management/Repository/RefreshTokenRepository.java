/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.bookstore.book_management.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import com.bookstore.book_management.Entity.RefreshToken;
/**
 *
 * @author Admin
 */
public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {
    List<RefreshToken> findByUserId(Long userId);
    RefreshToken findByToken(String token);

    boolean deleteByToken(String token);
}
