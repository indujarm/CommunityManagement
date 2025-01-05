package com.infosys.module1.controller;

import com.infosys.module1.config.JwtProvider;
import com.infosys.module1.dto.LoginDto;
import com.infosys.module1.exception.LoginException;
import com.infosys.module1.exception.SignupException;
import com.infosys.module1.model.User;
import com.infosys.module1.response.AuthResponse;
import com.infosys.module1.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping("/auth/signup")
    public AuthResponse signUp(@RequestBody User user) throws SignupException {
        return userService.signUp(user);
    }

    @PostMapping("/auth/login")
    public AuthResponse login(@RequestBody LoginDto loginDto) throws LoginException {
        return userService.login(loginDto);
    }
    @GetMapping("api/get-email")
    public String getEmailFromJWT(@RequestHeader("Authorization") String jwt){
        return JwtProvider.getEmailFromJwtToken(jwt);
    }
    @GetMapping("api/get-user")
    public User getUser(@RequestHeader ("Authorization") String jwt){
        return userService.getUser(jwt);
    }
}
