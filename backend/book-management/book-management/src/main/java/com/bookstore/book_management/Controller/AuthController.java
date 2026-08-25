package com.bookstore.book_management.Controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

    @PostMapping("/login")
    public ApiResponse<?> login(@Valid @RequestBody LoginRequest loginRequest) {
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
        return userService.createUser(user);
    }
}
