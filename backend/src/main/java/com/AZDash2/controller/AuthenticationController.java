package com.AZDash2.controller;

import java.io.IOException;
import java.net.URISyntaxException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.AZDash2.service.AuthenticationService;
import com.AZDash2.valueobject.LoginRequest;
import com.AZDash2.valueobject.LoginResponse;


@RestController
public class AuthenticationController {
    Logger logger = LoggerFactory.getLogger(AuthenticationController.class);
    
    @Autowired
    private AuthenticationService authService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest) throws URISyntaxException, IOException, InterruptedException {
        LoginResponse loginResponse;
        try {
            loginResponse = authService.authenticateUser(loginRequest.getUsername(), loginRequest.getPassword());
            if (!loginResponse.getSuccess()) {
                return new ResponseEntity<>(loginResponse, HttpStatus.UNAUTHORIZED);
            }
            return new ResponseEntity<>(loginResponse, HttpStatus.OK);
        } catch (URISyntaxException | IOException | InterruptedException e) {
            logger.error("Login failed", e);
            LoginResponse errorResponse = new LoginResponse();
            errorResponse.setSuccess(false);
            errorResponse.setMessage("Server error");
            errorResponse.setUsername("error");
            return new ResponseEntity<>(errorResponse, HttpStatus.UNAUTHORIZED);
        }
    }
}


