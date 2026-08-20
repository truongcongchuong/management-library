package com.bookstore.book_management.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bookstore.book_management.Dto.AuthResponse;
import com.bookstore.book_management.Dto.LoginRequest;
import com.bookstore.book_management.Dto.RefreshRequest;
import com.bookstore.book_management.Service.AuthService;

@RestController
@RequestMapping("/auth")
public class AuthController {
    
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @RequestMapping("/login")
    public AuthResponse login(LoginRequest loginRequest) {
        return authService.login(loginRequest);
    }

    @RequestMapping("/refresh")
    public AuthResponse refreshToken(RefreshRequest refreshRequest) {
        return authService.refreshToken(refreshRequest.getRefreshToken());
    }

    @RequestMapping("/logout")
    public void logout(RefreshRequest refreshRequest) {
        authService.logout(refreshRequest.getRefreshToken());
    }
}
