package com.infosys.community.config;

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
        String jwt=request.getHeader(Constants_JWT.JWT_HEADER);
        if (jwt!=null){
            try{
                String email= Provider.getEmailFromJwtToken(jwt);
            }
            catch(Exception e){
                throw new BadCredentialsException("Invalid Token");
            }
        }
        filterChain.doFilter(request,response);
    }
}