package com.infosys.community.config;

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
    private static final String ISSUER = "CommunityManagementSystem";

    public static String generateToken(Authentication auth) {
        return Jwts.builder()
                .setIssuer(ISSUER)
                .setIssuedAt(new Date())
                .setExpiration(new Date(new Date().getTime() + 86400000)) // Token expiration: 1 day
                .claim("email", auth.getName()) // Add email to claims
                .signWith(key)
                .compact();
    }

    public static boolean validateToken(String jwt) {
        try {
            // Remove "Bearer " prefix if present
            if (jwt.startsWith("Bearer ")) {
                jwt = jwt.substring(7);
            }

            // Parse the token to validate it
            Jwts.parser().setSigningKey(key).build().parseClaimsJws(jwt);
            return true; // If no exception, token is valid
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
        return false; // Token is invalid if any exception occurs
    }

    public static String getEmailFromJwtToken(String jwt) {
        // Remove "Bearer " prefix
        if (jwt.startsWith("Bearer ")) {
            jwt = jwt.substring(7);
        }

        Claims claims = Jwts.parser().setSigningKey(key).build().parseClaimsJws(jwt).getBody();
        return claims.get("email", String.class); // Extract email claim
    }
}
