/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.bookstore.book_management.Service;

import org.springframework.stereotype.Service;
import com.bookstore.book_management.Repository.UserRepository;
import com.bookstore.book_management.Entity.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.core.Authentication;
import com.bookstore.book_management.Dto.ApiResponse;
import org.springframework.dao.DataIntegrityViolationException;

/**
 *
 * @author Admin
 */

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    private final AuthService authService;

    public UserService(UserRepository userRepository, AuthService authService) {
        this.userRepository = userRepository;
        this.authService = authService;
    }

    public ApiResponse<?> getUserById(Long id, Authentication authentication
    ) {

        try {
            if (authentication != null&& !authService.canAccessUser(authentication, id)) {

                return ApiResponse.forbidden("Access denied");
            }

            User user = userRepository.findById(id).orElse(null);

            if (user == null) {
                return ApiResponse.notFound("User Not Found");
            }

            return ApiResponse.ok(user);
        } catch (Exception e) {
            return ApiResponse.internalServerError();
        }

    }

    public ApiResponse<?> createUser(User user) {
       try {

            user.setPassword(passwordEncoder.encode(user.getPassword()));
            userRepository.save(user);

            return ApiResponse.created(user);

        } catch (DataIntegrityViolationException e) {

            return ApiResponse.conflict(
                    "Email already exists"
            );

        } catch (Exception e) {

            return ApiResponse.internalServerError();
        }
    }

    public ApiResponse<?> deleteUser(Long id) {

        try {

            if (!userRepository.existsById(id)) {
                return ApiResponse.notFound(
                    "User not found"
                );
            }

            userRepository.deleteById(id);

            return ApiResponse.ok(null, "User Delete Successfully");
        } catch (Exception e) {
            return ApiResponse.internalServerError();
        }
    }

    public ApiResponse<?> updateUser(Long id, User updatedUser, Authentication authentication) {

        try {
            if (!authService.canAccessUser(authentication, id)) {
                return ApiResponse.forbidden("Access denied");
            }

            User existingUser = userRepository.findById(id).orElse(null);

            if (existingUser != null) {
                existingUser.setUsername(updatedUser.getUsername());
                existingUser.setEmail(updatedUser.getEmail());
                existingUser.setPassword(updatedUser.getPassword());

                userRepository.save(existingUser);

                return ApiResponse.ok(null, "Update Successfully");
            }

            return ApiResponse.notFound("User Not Found");

        } catch (Exception e) {
            return ApiResponse.internalServerError();
        }
    }

    public ApiResponse<?> getAllUsers() {
        try {
            return ApiResponse.ok(userRepository.findAll());
        } catch (Exception e) {
            return ApiResponse.internalServerError();
        }
        
    }

    public ApiResponse<?> findByUsername(String username) {

        try {
            return ApiResponse.ok(userRepository.findByUsername(username));
        } catch (Exception e) {
            return ApiResponse.internalServerError();
        }
        
    }
}
