/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.bookstore.book_management.Service;

import org.springframework.stereotype.Service;
import com.bookstore.book_management.Repository.RefreshTokenRepository;
import com.bookstore.book_management.Entity.RefreshToken;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Admin
 */
@Service
public class RefreshTokenService {
    private final RefreshTokenRepository refreshTokenRepository;

    public RefreshTokenService(RefreshTokenRepository refreshTokenRepository) {
        this.refreshTokenRepository = refreshTokenRepository;
    }

    public RefreshToken createRefreshToken(RefreshToken refreshToken) {
        return refreshTokenRepository.save(refreshToken);
    }

    public RefreshToken getRefreshTokenByToken(String token) {
        return refreshTokenRepository.findByToken(token);
    }

    public void deleteRefreshToken(Long id) {
        refreshTokenRepository.deleteById(id);
    }

    public void deleteRefreshTokensByUserId(Long userId) {
        refreshTokenRepository.findByUserId(userId).forEach(refreshTokenRepository::delete);
    }
    
    @Transactional
    public void logout(String token) {
        refreshTokenRepository.deleteByToken(token);
    }
}
