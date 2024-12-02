package com.infosys.community.controller;

import com.infosys.community.config.Provider;
import com.infosys.community.exception.LoginException;
import com.infosys.community.model.User;
import com.infosys.community.repository.UserRepository;
import com.infosys.community.request.LoginRequest;
import com.infosys.community.response.AuthResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class LoginController {

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserRepository userRepository;
    @PostMapping("/auth/signin")
    public AuthResponse signin(@RequestBody LoginRequest loginDetails) throws LoginException {
        Authentication authentication=authenticate(loginDetails.getEmail(),loginDetails.getPassword());
        String token= Provider.generateToken(authentication);
        return new AuthResponse(token,"Login Success");
    }
    public Authentication authenticate(String email,String password) throws LoginException {
        UserDetails userDetails=getDetails(email);
        if (userDetails==null){
            throw new LoginException("User Not Found");
        }
        if (!passwordEncoder.matches(password,userDetails.getPassword())){
            throw new LoginException("Password does not match");
        }
        return new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
    }
    public UserDetails getDetails(String email) throws LoginException {
        User user=userRepository.findByEmail(email);
        if (user==null){
            throw new LoginException("User Not Found with email "+email);
        }
        List<GrantedAuthority> authorities=new ArrayList<>();
        if ("admin".equals(user.getRole())) {
            authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
        } else if ("resident".equals(user.getRole())) {
            authorities.add(new SimpleGrantedAuthority("ROLE_RESIDENT"));
        }
        return new org.springframework.security.core.userdetails.User(user.getEmail(),user.getPassword(),authorities);
    }
}