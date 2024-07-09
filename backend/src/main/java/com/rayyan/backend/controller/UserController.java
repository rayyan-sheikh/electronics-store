package com.rayyan.backend.controller;


import com.rayyan.backend.entity.User;
import com.rayyan.backend.service.UserService;
import com.rayyan.backend.utils.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public ApiResponse getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public ApiResponse getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }


//    @PutMapping("/{id}")
//    public ApiResponse updateUser(@PathVariable Long id, @RequestBody UserDTO userDTO) {
//        return userService.updateUser(id, userDTO);
//    }

    @DeleteMapping("/{id}")
    public ApiResponse deleteUser(@PathVariable Long id) {
        return userService.deleteUser(id);
    }
}
