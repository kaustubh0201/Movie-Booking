package com.project.moviebooking.service;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.core.Authentication;

import java.util.List;

public interface JwtService {
    String generateAccessToken(String username, List<String> roles);
    String generateRefreshToken(String username, List<String> roles);
    String resolveToken(HttpServletRequest request);
    Authentication getAuthentication(String token);
    boolean isTokenValid(String token, String user);
    boolean isTokenExpired(String token);
    String extractUsername(String token);
}
