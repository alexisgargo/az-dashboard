package com.AZDash2.service;

import java.io.IOException;
import java.net.URISyntaxException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;

import com.AZDash2.entity.Admin;
import com.AZDash2.repository.AdminRepository;
import com.AZDash2.valueobject.LoginResponse;

@Service
public class AuthenticationService {
    Logger logger = LoggerFactory.getLogger(AuthenticationService.class);

    @Autowired
    private AdminRepository adminRepository;

    public LoginResponse authenticateUser(String username, String plainPassword) throws URISyntaxException, IOException, InterruptedException {
        LoginResponse loginResponse = new LoginResponse();
        Admin admin = adminRepository.findByUsername(username);
        if (admin != null) {
            
            if (BCrypt.checkpw(plainPassword, admin.getAdminPassword()) == true) {
                loginResponse.setSuccess(true);
                loginResponse.setMessage("Login Successful");
                loginResponse.setUsername(username);
                return loginResponse;
            }
        }
        loginResponse.setSuccess(false);
        loginResponse.setMessage("Invalid Username or Password");
        loginResponse.setUsername(username);
        return loginResponse;

    }

}
