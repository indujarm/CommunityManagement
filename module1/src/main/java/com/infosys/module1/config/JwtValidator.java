package com.infosys.module1.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

public class JwtValidator extends OncePerRequestFilter {
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String jwt=request.getHeader(JwtConstant.JWT_HEADER);
        if (jwt!=null){
            try{
                String email=JwtProvider.getEmailFromJwtToken(jwt);
                System.out.println("Received JWT: " + jwt);  // Log to see if token is received
                System.out.println("Email extracted from token: " + email);
            }
            catch(Exception e){
                System.out.println("Invalid Token: " + e.getMessage());
                throw new BadCredentialsException("Invalid Token");
            }
        }
        filterChain.doFilter(request,response);
    }
}
