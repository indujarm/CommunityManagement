package com.infosys.community.service;

import com.infosys.community.dto.LoginDto;
import com.infosys.community.exception.Loginexception;
import com.infosys.community.exception.SignupException;
import com.infosys.community.model.User;
import com.infosys.community.config.Provider;
import com.infosys.community.repository.UserRepository;


import com.infosys.community.response.AuthResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImplementation implements UserService{

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;


    @Override
    public AuthResponse signUp(User user) throws SignupException {
        User isExist = userRepository.findByEmail(user.getEmail());

        if(isExist != null){
            throw new SignupException("user with email already exists");
        }
        User newUser=new User();
        newUser.setEmail(user.getEmail());
        newUser.setPassword(passwordEncoder.encode(user.getPassword()));
        newUser.setRole(user.getRole());
        userRepository.save(newUser);
        Authentication authentication=new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword());
        String token = Provider.generateToken(authentication);
        return new AuthResponse(token,"SignUp Successful");

    }

    @Override
    public AuthResponse login(LoginDto loginData) throws Loginexception {
        Authentication authentication=authenticate(loginData.getEmail(),loginData.getPassword());
        String token= Provider.generateToken(authentication);
        return new AuthResponse(token,"Login Success");
    }

    @Override
    public User getUser(String jwt) {
        String email=Provider.getEmailFromJwtToken(jwt);
        return userRepository.findByEmail(email);
    }

    public Authentication authenticate(String email,String password) throws Loginexception {
        UserDetails userDetails=getDetails(email);
        if (userDetails==null){
            throw new Loginexception("User Not Found");
        }
        if (!passwordEncoder.matches(password,userDetails.getPassword())){
            throw new Loginexception("Password does not match");
        }
        return new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
    }
    public UserDetails getDetails(String email) throws Loginexception {
        User user=userRepository.findByEmail(email);
        if (user==null){
            throw new Loginexception("User Not Found with email "+email);
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


