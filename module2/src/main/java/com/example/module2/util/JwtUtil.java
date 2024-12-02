package com.example.module2.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Base64;
import java.util.Date;

@Component
public class JwtUtil {

    private static final String BASE64_SECRET = "Vg5iwkAhQSsWN5tpcWW0HS9TGIHMu1BvPuuYsCeXB7A=";
    private static final SecretKey SECRET_KEY = Keys.hmacShaKeyFor(
            Base64.getDecoder().decode(BASE64_SECRET)
    );

    // Generate a JWT token
    public String generateToken(String email) {
        return Jwts.builder()
                .setSubject(email)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 86400000)) // Token valid for 1 day
                .signWith(SECRET_KEY) // Automatically uses HS256 with the provided key
                .compact();
    }

    // Extract email (subject) from a JWT token
    public String extractEmail(String token) {
        Claims claims = Jwts.parser() // Use parserBuilder for newer versions
                .setSigningKey(SECRET_KEY) // Set the signing key
                .build()
                .parseClaimsJws(token)
                .getBody();

        return claims.getSubject();
    }
}
