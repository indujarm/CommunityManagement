package com.example.module2.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class Provider {

    private static final SecretKey key = Keys.hmacShaKeyFor(Constants_JWT.SECRET_STRING.getBytes());
    private static final String ISSUER = "CommunityManagementSystem";  // You can change this as per your use case.

    // Generate JWT token for the authenticated user
    public static String generateToken(Authentication auth) {
        return Jwts.builder()
                .setIssuer(ISSUER)  // Issuer is typically the system generating the token
                .setIssuedAt(new Date())  // Token issue time
                .setExpiration(new Date(new Date().getTime() + 86400000))  // Token expiration: 1 day
                .claim("email", auth.getName())  // Store email in the claims (username is used here as email)
                .signWith(key)  // Sign with HMAC key
                .compact();
    }

    // Validate the JWT token
    public static boolean validateToken(String jwt) {
        try {
            // Remove "Bearer " prefix if present
            if (jwt.startsWith("Bearer ")) {
                jwt = jwt.substring(7);
            }

            // Parse and validate the JWT token
            Jwts.parser().setSigningKey(key).build().parseClaimsJws(jwt);
            return true; // If no exception, the token is valid
        } catch (ExpiredJwtException e) {
            System.err.println("Token expired: " + e.getMessage());
        } catch (UnsupportedJwtException e) {
            System.err.println("Unsupported JWT: " + e.getMessage());
        } catch (MalformedJwtException e) {
            System.err.println("Malformed JWT: " + e.getMessage());
        } catch (SignatureException e) {
            System.err.println("Invalid signature: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.err.println("Invalid token: " + e.getMessage());
        }
        return false;  // Token is invalid if any exception occurs
    }

    // Extract email (or username) from the JWT token
    public static String getEmailFromJwtToken(String jwt) {
        // Remove "Bearer " prefix if present
        if (jwt.startsWith("Bearer ")) {
            jwt = jwt.substring(7);
        }

        // Parse the JWT and extract the "email" claim
        Claims claims = Jwts.parser().setSigningKey(key).build().parseClaimsJws(jwt).getBody();
        return claims.get("email", String.class);  // Get email from the claims
    }
}
