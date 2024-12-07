package com.infosys.community.service;

import com.infosys.community.dto.LoginDto;

import com.infosys.community.exception.Loginexception;
import com.infosys.community.exception.SignupException;
import com.infosys.community.response.AuthResponse;
import com.infosys.community.model.User;

public interface UserService {
     public AuthResponse signUp(User user) throws SignupException;
     public AuthResponse login(LoginDto loginData) throws Loginexception, Loginexception;
     public User getUser(String jwt);

}