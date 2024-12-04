package com.example.module2.util;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

public class Validator extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String jwt = request.getHeader("Authorization");  // Get the JWT token from the Authorization header
        if (jwt != null && jwt.startsWith("Bearer ")) {
            jwt = jwt.substring(7);  // Remove the "Bearer " prefix

            try {
                // Use the Provider to extract email and validate token
                String email = Provider.getEmailFromJwtToken(jwt);
                // Optionally, set the email or other details in the security context if required
            } catch (Exception e) {
                throw new BadCredentialsException("Invalid Token: " + e.getMessage());
            }
        }
        filterChain.doFilter(request, response);  // Continue the filter chain
    }
}
