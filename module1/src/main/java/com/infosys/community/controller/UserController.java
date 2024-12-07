package com.infosys.community.controller;

import com.infosys.community.dto.LoginDto;
import com.infosys.community.exception.Loginexception;
import com.infosys.community.exception.SignupException;
import com.infosys.community.model.User;
import com.infosys.community.response.AuthResponse;
import com.infosys.community.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.security.auth.login.LoginException;
import com.infosys.community.config.Provider;

@RestController
@RequestMapping
public class UserController {

    @Autowired
    UserService userService ;
//    private UserRepository userRepository;

@PostMapping("/auth/signup")
public AuthResponse signUp(@RequestBody User user) throws SignupException {
    return userService.signUp(user);
}

    @PostMapping("/auth/login")
    public AuthResponse login(@RequestBody LoginDto loginDto) throws Loginexception {
        return userService.login(loginDto);
    }
    @GetMapping("/get-email")
    public String getEmailFromJWT(@RequestHeader("Authorization") String jwt){
        return Provider.getEmailFromJwtToken(jwt);
    }
    @GetMapping("/get-user")
    public User getUser(@RequestHeader ("Authorization") String jwt){
        return userService.getUser(jwt);
    }

}
