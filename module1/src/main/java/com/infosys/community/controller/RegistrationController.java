package com.infosys.community.controller;

import com.infosys.community.config.Provider;
import com.infosys.community.model.AdminDetails;
import com.infosys.community.model.ResidentDetails;
import com.infosys.community.model.User;
import com.infosys.community.repository.UserRepository;
import com.infosys.community.response.AuthResponse;
import com.infosys.community.service.RegistationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
public class RegistrationController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RegistationService registationService;

    @Autowired
    private Provider jwtProvider;

    @Autowired
    PasswordEncoder passwordEncoder;
    @PostMapping("/auth/signup")
    public AuthResponse signUp(@RequestBody User user) {
        User newUser = new User();
        newUser.setEmail(user.getEmail());
        newUser.setPassword(passwordEncoder.encode(user.getPassword()));
        newUser.setRole(user.getRole());
        userRepository.save(newUser);
        Authentication authentication=new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword());
        String token = Provider.generateToken(authentication);
        return new AuthResponse(token,"Register Success");
    }
    @PostMapping("/admin-register/{email}")
    public String adminRegistration(@PathVariable String email, @RequestBody AdminDetails adminDetails) {
        User user = userRepository.findByEmail(email);
        if (user == null || !"ADMIN".equalsIgnoreCase(user.getRole())) {
            return "Invalid registration request for admin.";
        }
        adminDetails.setUser(user);
        registationService.adminRegistration(adminDetails);
        return "Admin registered successfully!";
    }
    @PostMapping("/resident-register/{email}")
    public String residentRegistration(@PathVariable String email, @RequestBody ResidentDetails residentDetails) {
        User user = userRepository.findByEmail(email);
        if (user == null || !"RESIDENT".equalsIgnoreCase(user.getRole())) {
            return "Invalid registration request for resident.";
        }
        residentDetails.setUser(user);
        registationService.residentRegistration(residentDetails);
        return "Resident registered successfully!";
    }

}