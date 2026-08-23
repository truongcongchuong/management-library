package com.bookstore.book_management.Config;

import java.io.IOException;
import java.util.List;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.bookstore.book_management.Entity.User;
import com.bookstore.book_management.Service.JwtService;
import com.bookstore.book_management.Service.UserService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import com.bookstore.book_management.Dto.JwtAccess;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final UserService userService;

    public JwtAuthenticationFilter(
            JwtService jwtService,
            UserService userService) {

        this.jwtService = jwtService;
        this.userService = userService;
    }

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain)
            throws ServletException, IOException {

        String authHeader = request.getHeader("Authorization");

        if (authHeader == null
                || !authHeader.startsWith("Bearer ")) {

            filterChain.doFilter(request, response);
            return;
        }

        try {

            String token = authHeader.substring(7);

            if (!jwtService.validateAccessToken(token)) {

                filterChain.doFilter(request, response);
                return;
            }

            Long userId = jwtService.extractUserId(token);

            User user = (User) userService.getUserById(userId, null).getData();

            if (user == null) {

                filterChain.doFilter(request, response);
                return;
            }

            String role = jwtService.extractRole(token);

            List<SimpleGrantedAuthority> authorities =List.of(new SimpleGrantedAuthority("ROLE_" + role));

            JwtAccess jwt = new JwtAccess(user.getId(), user.getUsername(), role);

            UsernamePasswordAuthenticationToken authentication =
                    new UsernamePasswordAuthenticationToken(
                            jwt,
                            null,
                            authorities);

            SecurityContextHolder
                    .getContext()
                    .setAuthentication(authentication);

        } catch (Exception e) {

            SecurityContextHolder.clearContext();
        }

        filterChain.doFilter(request, response);
    }
}