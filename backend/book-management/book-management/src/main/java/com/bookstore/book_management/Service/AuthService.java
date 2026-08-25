package com.bookstore.book_management.Service;

import java.time.LocalDateTime;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.bookstore.book_management.Dto.ApiResponse;
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

    public ApiResponse<?> login(LoginRequest loginRequest) {

        try {

            User user =
                    userRepository.findByEmail(
                            loginRequest.getEmail()
                    );

            if (user == null || !passwordEncoder.matches(
                loginRequest.getPassword(),user.getPassword())
            ) {

                return ApiResponse.unauthorized(
                        "Invalid email or password"
                );
            }

            String accessToken =
                    jwtService.generateAccessToken(
                            user.getId(),
                            user.getUsername(),
                            user.getRole().getName()
                    );

            String refreshToken =
                    jwtService.generateRefreshToken(
                            user.getId()
                    );

            refreshTokenService.createRefreshToken(
                    new RefreshToken(
                            refreshToken,
                            LocalDateTime.now().plusDays(7),
                            user
                    )
            );

            return ApiResponse.ok(
                    new AuthResponse(
                            accessToken,
                            refreshToken
                    )
            );

        } catch (Exception e) {

            return ApiResponse.internalServerError();
        }
    }

    public ApiResponse<?> register(User user) {
        try {
            if (userRepository.findByEmail(user.getEmail()) != null) {
                return ApiResponse.conflict("Email already exists");
            }

            userService.createUser(user);
            return ApiResponse.ok(null, "User registered successfully");
        } catch (Exception e) {
            return ApiResponse.internalServerError();
        }
        
    }

    public ApiResponse<?> refreshToken(String refreshToken) {

        try {

            if (!jwtService.validateRefreshToken(refreshToken)) {

                return ApiResponse.unauthorized(
                        "Invalid or expired refresh token"
                );
            }

            if (refreshTokenService
                    .getRefreshTokenByToken(refreshToken) == null) {

                return ApiResponse.unauthorized(
                        "Refresh token not found"
                );
            }

            Long userId =
                    jwtService.extractUserId(refreshToken);

            User user =
                    userRepository.findById(userId)
                            .orElse(null);

            if (user == null) {

                return ApiResponse.notFound(
                        "User not found"
                );
            }

            String newAccessToken =
                    jwtService.generateAccessToken(
                            user.getId(),
                            user.getUsername(),
                            user.getRole().getName()
                    );

            AuthResponse response =
                    new AuthResponse(
                            newAccessToken,
                            refreshToken
                    );

            return ApiResponse.ok(
                    response,
                    "Access token refreshed successfully"
            );

        } catch (Exception e) {

            return ApiResponse.internalServerError();
        }
    }

    public ApiResponse<?> logout(String refreshToken) {

        try {

            if (!jwtService.validateRefreshToken(refreshToken)) {

                return ApiResponse.unauthorized(
                        "Invalid or expired refresh token"
                );
            }

            if (refreshTokenService.getRefreshTokenByToken(refreshToken) == null) {

                return ApiResponse.notFound(
                        "Refresh token not found"
                );
            }

            refreshTokenService.logout(refreshToken);

            return ApiResponse.ok(
                    null,
                    "Logout successful"
            );

        } catch (Exception e) {
                System.out.println(e);
            return ApiResponse.internalServerError();
        }
    }
}