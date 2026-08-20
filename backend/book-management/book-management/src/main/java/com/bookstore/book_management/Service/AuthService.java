package com.bookstore.book_management.Service;

import java.time.LocalDateTime;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.bookstore.book_management.Dto.AuthResponse;
import com.bookstore.book_management.Dto.LoginRequest;
import com.bookstore.book_management.Entity.RefreshToken;
import com.bookstore.book_management.Entity.User;
import com.bookstore.book_management.Repository.UserRepository;

@Service
public class AuthService {
    
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    private final JwtService jwtService;
    private final UserService userService;
    private final RefreshTokenService refreshTokenService;

    public AuthService(UserRepository userRepository, JwtService jwtService, UserService userService, RefreshTokenService refreshTokenService) {
        this.userRepository = userRepository;
        this.jwtService = jwtService;
        this.userService = userService;
        this.refreshTokenService = refreshTokenService;
    }

    public AuthResponse login(LoginRequest loginRequest) {
        User user = userRepository.findByEmail(loginRequest.getEmail());

        if (user == null) {
            return new AuthResponse(null, null);
        }

        boolean isValid =
        passwordEncoder.matches(
                loginRequest.getPassword(),
                user.getPassword()
        );

        if (!isValid) {
            return new AuthResponse(null, null);
        }


        String accessToken = jwtService.generateAccessToken(user.getId(), user.getUsername(), user.getRole().getName());
        String refreshToken = jwtService.generateRefreshToken(user.getId());

        refreshTokenService.createRefreshToken(new RefreshToken(refreshToken, LocalDateTime.now().plusHours(24)));

        return new AuthResponse(accessToken, refreshToken);
    }

    public String register(User user) {
        if (userRepository.findByEmail(user.getEmail()) != null) {
            return "Email already exists";
        }

        userService.createUser(user);
        return "User registered successfully";
    }

    public AuthResponse refreshToken(String refreshToken) {
        try {

            if (!jwtService.validateRefreshToken(refreshToken)) {
                return new AuthResponse(null, null);
            }

            if (refreshTokenService.getRefreshTokenByToken(refreshToken) == null) {
                return new AuthResponse(null, null);
                
            }

            Long userId = jwtService.extractUserId(refreshToken);
            User user = userService.getUserById(userId);

            if (user != null) {
                String newAccessToken = jwtService.generateAccessToken(user.getId(), user.getUsername(), user.getRole().getName());
                return new AuthResponse(newAccessToken, refreshToken);
            } else {
                return new AuthResponse(null, null);
            }
        } catch (Exception e) {
            return new AuthResponse(null, null);
        }
    }

    public String logout(String token) {
        if (!jwtService.validateRefreshToken(token)) {
            return "Invalid refresh token";
        }

        refreshTokenService.logout(token);
        return "Logout successful";
    }
}