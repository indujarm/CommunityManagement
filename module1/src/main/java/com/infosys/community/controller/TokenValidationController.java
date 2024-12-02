package com.infosys.community.controller;

import com.infosys.community.config.Provider;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TokenValidationController {

    @PostMapping("/auth/validate-token")
    public boolean validateTokenUsingBody(@RequestBody String token) {
        try {
            String email = Provider.getEmailFromJwtToken(token);
            return email != null; // Return true if token is valid
        } catch (Exception e) {
            return false; // Return false for invalid tokens
        }
    }

    @PostMapping("/validate-token")
    public ResponseEntity<?> validateTokenUsingHeader(@RequestHeader("Authorization") String token) {
        if (Provider.validateToken(token)) {
            return ResponseEntity.ok("Token is valid");
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid token");
    }
}
