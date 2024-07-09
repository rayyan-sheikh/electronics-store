package com.rayyan.backend.controller;


import com.rayyan.backend.dto.LoginRequest;
import com.rayyan.backend.entity.User;
import com.rayyan.backend.service.UserService;
import com.rayyan.backend.utils.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    UserService userService;

    @PostMapping("/register")
    public ResponseEntity<ApiResponse> registerUser(@RequestBody User user) {
        ApiResponse response = userService.registerUser(user);
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse> loginUser(@RequestBody LoginRequest loginRequest) {
        ApiResponse response = userService.loginUser(loginRequest);
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }
}
