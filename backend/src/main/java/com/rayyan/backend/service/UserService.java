package com.rayyan.backend.service;


import com.rayyan.backend.dto.LoginRequest;
import com.rayyan.backend.dto.UserDTO;
import com.rayyan.backend.entity.User;
import com.rayyan.backend.exception.OurException;
import com.rayyan.backend.repository.UserRepository;
import com.rayyan.backend.utils.ApiResponse;
import com.rayyan.backend.utils.DtoConverter;
import com.rayyan.backend.utils.JWTUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JWTUtils jwtUtils;
    @Autowired
    private AuthenticationManager authenticationManager;

    public ApiResponse registerUser(User user){
        ApiResponse response = new ApiResponse();
        try {
            if (user.getRole() == null || user.getRole().isBlank()) {
                user.setRole("USER");
            }
            if (userRepository.existsByEmail(user.getEmail())) {
                throw new RuntimeException(user.getEmail() + "Already Exists");
            }
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            User savedUser = userRepository.save(user);
            UserDTO userDTO = DtoConverter.toUserDTO(savedUser);
            response.setStatusCode(200);
            response.setUser(userDTO);
        } catch (RuntimeException e) {
            response.setStatusCode(400);
            response.setMessage(e.getMessage());
        } catch (Exception e) {
            response.setStatusCode(500);
            response.setMessage("Error Occurred During User Registration " + e.getMessage());

        }
        return response;
    }

    public ApiResponse loginUser(LoginRequest loginRequest) {

        ApiResponse response = new ApiResponse();

        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));
            var user = userRepository.findByEmail(loginRequest.getEmail()).orElseThrow(() -> new UsernameNotFoundException("User not found"));

            var token = jwtUtils.generateToken(user);
            response.setStatusCode(200);
            response.setToken(token);
            response.setRole(user.getRole());
            response.setExpirationTime("7 Days");
            response.setMessage("successful");

        } catch (OurException e) {
            response.setStatusCode(404);
            response.setMessage(e.getMessage());

        } catch (Exception e) {

            response.setStatusCode(500);
            response.setMessage("Error Occurred During User Login " + e.getMessage());
        }
        return response;
    }

    public ApiResponse getAllUsers() {
        List<User> users = userRepository.findAll();
        List<UserDTO> userDTOs = users.stream()
                .map(DtoConverter::toUserDTO)
                .collect(Collectors.toList());

        ApiResponse response = new ApiResponse();
        response.setStatusCode(200);
        response.setMessage("Users retrieved successfully");
        response.setUserList(userDTOs);
        return response;
    }

    public ApiResponse getUserById(Long id) {

        Optional<User> userOptional = userRepository.findById(id);
        ApiResponse response = new ApiResponse();

        if (userOptional.isPresent()) {
            UserDTO userDTO = DtoConverter.toUserDTO(userOptional.get());
            response.setStatusCode(200);
            response.setMessage("User retrieved successfully");
            response.setUser(userDTO);
        } else {
            response.setStatusCode(404);
            response.setMessage("User not found");
        }

        return response;
    }

//    public ApiResponse createUser(User user) {
//        user = userRepository.save(user);
//        UserDTO savedUserDTO = DtoConverter.toUserDTO(user);
//
//        ApiResponse response = new ApiResponse();
//        response.setStatusCode(201);
//        response.setMessage("User created successfully");
//        response.setUser(savedUserDTO);
//        return response;
//    }

//    public ApiResponse updateUser(Long id, UserDTO userDTO) {
//        Optional<User> userOptional = userRepository.findById(id);
//        ApiResponse response = new ApiResponse();
//
//        if (userOptional.isPresent()) {
//            User user = userOptional.get();
//            user.setEmail(userDTO.getEmail());
//            user.setName(userDTO.getName());
//            user.setPhoneNumber(userDTO.getPhoneNumber());
//            user.setRole(userDTO.getRole());
//            user.setAddress(AddressMapper.toAddress(userDTO.getAddress()));
//            user.setPayments(userDTO.getPayments().stream()
//                    .map(PaymentMapper::toPayment)
//                    .collect(Collectors.toList()));
//            user.setCarts(userDTO.getCarts().stream()
//                    .map(CartMapper::toCart)
//                    .collect(Collectors.toList()));
//            user.setOrders(userDTO.getOrders().stream()
//                    .map(OrderMapper::toOrder)
//                    .collect(Collectors.toList()));
//
//            user = userRepository.save(user);
//            UserDTO updatedUserDTO = UserMapper.toUserDTO(user);
//            response.setStatusCode(200);
//            response.setMessage("User updated successfully");
//            response.setUser(updatedUserDTO);
//        } else {
//            response.setStatusCode(404);
//            response.setMessage("User not found");
//        }
//
//        return response;
//    }

    public ApiResponse deleteUser(Long id) {
        Optional<User> userOptional = userRepository.findById(id);
        ApiResponse response = new ApiResponse();

        if (userOptional.isPresent()) {
            userRepository.deleteById(id);
            response.setStatusCode(200);
            response.setMessage("User deleted successfully");
        } else {
            response.setStatusCode(404);
            response.setMessage("User not found");
        }

        return response;
    }
}
