package com.bookstore.book_management.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bookstore.book_management.Dto.ApiResponse;
import com.bookstore.book_management.Dto.LoginRequest;
import com.bookstore.book_management.Dto.RefreshRequest;
import com.bookstore.book_management.Service.AuthService;
import com.bookstore.book_management.Service.UserService;

import com.bookstore.book_management.Entity.User;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthController {
    
    private final AuthService authService;
    private final UserService userService;

    public AuthController(AuthService authService, UserService userService) {
        this.authService = authService;
        this.userService = userService;
    }

    @RequestMapping("/login")
    public ApiResponse<?> login(@Valid LoginRequest loginRequest) {
        return authService.login(loginRequest);
    }

    @RequestMapping("/refresh")
    public ApiResponse<?> refreshToken(RefreshRequest refreshRequest) {
        return authService.refreshToken(refreshRequest.getRefreshToken());
    }

    @RequestMapping("/logout")
    public ApiResponse<?> logout(RefreshRequest refreshRequest) {
        return authService.logout(refreshRequest.getRefreshToken());
    }

    @RequestMapping("/register")
    public ApiResponse<?> register(@Valid User user) {
        return userService.createUser(user);
    }
}
