/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.bookstore.book_management.Service;

import java.nio.charset.StandardCharsets;
import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.stereotype.Service;

import com.bookstore.book_management.Dto.JwtAccess;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.Authentication;
/**
 *
 * @author Admin
 */

@Service
public class JwtService {
     private final String SECRET =
            "mysecretkeymysecretkeymysecretkeymysecretkey"; // Use a strong secret key in production

    private SecretKey getSigningKey() {
        return Keys.hmacShaKeyFor(
                SECRET.getBytes(StandardCharsets.UTF_8)
        );
    }

    public String generateAccessToken(Long id, String username, String role) {
        return Jwts.builder()
                .subject(id.toString())
                .claim("username", username)
                .claim("role", role)
                .claim("type", "access")
                .issuedAt(new Date())
                .expiration(
                        new Date(
                                System.currentTimeMillis()
                                        + 1000 * 60 * 60
                        )
                )
                .signWith(getSigningKey())
                .compact();
    }

    public String generateRefreshToken(Long id) {
        return Jwts.builder()
                .subject(id.toString())
                .claim("type", "refresh")
                .issuedAt(new Date())
                .expiration(
                        new Date(System.currentTimeMillis()+ 1000 * 60 * 60 * 24 * 7)
                )
                .signWith(getSigningKey())
                .compact();
    }

    public Long extractUserId(String token) {
        return Long.parseLong(Jwts.parser()
                .verifyWith(getSigningKey())
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject());
    }

    public Claims extractAllClaims(String token) {
        return Jwts.parser()
                .verifyWith(getSigningKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    public String extractUsername(String token) {
        return extractAllClaims(token).get("username", String.class);
    }

    public String extractRole(String token) {
        return extractAllClaims(token)
                .get("role", String.class);
    }

    public String extractType(String token) {
        return extractAllClaims(token)
                .get("type", String.class);
    }

    public Date extractExpiration(String token) {
        return extractAllClaims(token)
                .getExpiration();
    }

    public boolean isTokenExpired(String token) {
        return extractExpiration(token)
                .before(new Date());
    }

    public boolean isAccessToken(String token) {
        return "access".equals(extractType(token));
    }

    public boolean isRefreshToken(String token) {
        return "refresh".equals(
                extractType(token)
        );
    }

    public boolean validateAccessToken(String token) {

        try {

                if (isTokenExpired(token)) {
                return false;
                }

                return isAccessToken(token);

        } catch (Exception e) {

                return false;
        }
    }

    public boolean validateRefreshToken(String token) {

        try {

                if (isTokenExpired(token)) {
                return false;
                }

                return isRefreshToken(token);

        } catch (Exception e) {

                return false;
        }
    }

        public boolean canAccessUser(Authentication authentication, Long id) {

                JwtAccess jwt = (JwtAccess) authentication.getPrincipal();

                return !(
                        jwt.getRole().equals("USER")
                        && !jwt.getId().equals(id)
                );
        }
}
