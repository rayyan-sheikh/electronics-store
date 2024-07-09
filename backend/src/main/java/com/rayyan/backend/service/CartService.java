package com.rayyan.backend.service;


import com.rayyan.backend.dto.CartDTO;
import com.rayyan.backend.entity.Cart;
import com.rayyan.backend.repository.CartRepository;
import com.rayyan.backend.utils.ApiResponse;
import com.rayyan.backend.utils.DtoConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CartService {

    @Autowired
    private CartRepository cartRepository;


    public ApiResponse getAllCarts(){
        List<Cart> carts = cartRepository.findAll();
        List<CartDTO> cartDTOS = carts.stream()
                .map(DtoConverter::toCartDTO)
                .collect(Collectors.toList());

        ApiResponse response = new ApiResponse();
        response.setStatusCode(200);
        response.setMessage("Carts retrieved successfully");
        response.setCartList(cartDTOS);

        return response;
    }


    public ApiResponse getCartById(Long id){
        Cart cart = cartRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Cart not found with id " + id));
        CartDTO cartDTO = DtoConverter.toCartDTO(cart);

        ApiResponse response = new ApiResponse();
        response.setStatusCode(200);
        response.setMessage("Cart retrieved successfully");
        response.setCart(cartDTO);

        return response;
    }



    // Method to create a new cart
    @Transactional
    public ApiResponse createCart(Cart cart) {
        Cart savedCart = cartRepository.save(cart);
        CartDTO cartDTO = DtoConverter.toCartDTO(savedCart);
        ApiResponse response = new ApiResponse();
        response.setStatusCode(201);
        response.setMessage("Cart created successfully");
        response.setCart(cartDTO);
        return response;
    }
//
//    // Method to update an existing cart
//    @Transactional
//    public ApiResponse updateCart(Long id, Cart cartDetails) {
//        Cart cart = cartRepository.findById(id)
//                .orElseThrow(() -> new RuntimeException("Cart not found with id " + id));
//        // Update fields as needed
//        // For example:
//        // cart.setTotal(cartDetails.getTotal());
//        Cart updatedCart = cartRepository.save(cart);
//        ApiResponse response = new ApiResponse();
//        response.setStatusCode(200);
//        response.setMessage("Cart updated successfully");
//        response.setCart(updatedCart);
//        return response;
//    }
//
//    // Method to delete a cart by ID
//    @Transactional
//    public ApiResponse deleteCart(Long id) {
//        Cart cart = cartRepository.findById(id)
//                .orElseThrow(() -> new RuntimeException("Cart not found with id " + id));
//        cartRepository.delete(cart);
//        ApiResponse response = new ApiResponse();
//        response.setStatusCode(204);
//        response.setMessage("Cart deleted successfully");
//        return response;
//    }
}
