package com.bookstore.book_management.Controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bookstore.book_management.Dto.ApiResponse;
import com.bookstore.book_management.Dto.LoginRequest;
import com.bookstore.book_management.Dto.RefreshRequest;
import com.bookstore.book_management.Service.AuthService;

import com.bookstore.book_management.Entity.User;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthController {
    
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ApiResponse<?> login(@Valid @RequestBody LoginRequest loginRequest) {
        System.out.println(loginRequest);
        return authService.login(loginRequest);
    }

    @PostMapping("/refresh")
    public ApiResponse<?> refreshToken(@Valid @RequestBody RefreshRequest refreshRequest) {
        return authService.refreshToken(refreshRequest.getRefreshToken());
    }

    @PostMapping("/logout")
    public ApiResponse<?> logout(@Valid @RequestBody RefreshRequest refreshRequest) {
        return authService.logout(refreshRequest.getRefreshToken());
    }

    @PostMapping("/register")
    public ApiResponse<?> register(@Valid @RequestBody User user) {
        return authService.register(user);
    }
}
