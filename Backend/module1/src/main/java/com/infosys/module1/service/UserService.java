package com.infosys.module1.service;

import com.infosys.module1.dto.LoginDto;
import com.infosys.module1.exception.LoginException;
import com.infosys.module1.exception.SignupException;
import com.infosys.module1.model.User;
import com.infosys.module1.response.AuthResponse;

public interface UserService {
    public AuthResponse signUp(User user) throws SignupException;
    public AuthResponse login(LoginDto loginData) throws LoginException;
    public User getUser(String jwt);

}
