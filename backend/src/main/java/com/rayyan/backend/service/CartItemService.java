package com.rayyan.backend.service;


import com.rayyan.backend.dto.CartItemDTO;
import com.rayyan.backend.entity.CartItem;
import com.rayyan.backend.repository.CartItemRepository;
import com.rayyan.backend.utils.ApiResponse;
import com.rayyan.backend.utils.DtoConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CartItemService {

    @Autowired
    private CartItemRepository cartItemRepository;

    public ApiResponse getAllCartItems(){
        List<CartItem> cartItems = cartItemRepository.findAll();
        List<CartItemDTO> cartItemDTOs = cartItems.stream()
                .map(DtoConverter::toCartItemDTO)
                .collect(Collectors.toList());

        ApiResponse response = new ApiResponse();
        response.setStatusCode(200);
        response.setMessage("Cart items retrieved successfully");
        response.setCartItemList(cartItemDTOs);
        return response;
    }

    public ApiResponse getCartItemById(Long id){

        CartItem cartItem = cartItemRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("No Cart Item found with id " +id));

        CartItemDTO cartItemDTO = DtoConverter.toCartItemDTO(cartItem);

        ApiResponse response = new ApiResponse();
        response.setStatusCode(200);
        response.setMessage("Cart item retrieved successfully");
        response.setCartItem(cartItemDTO);

        return response;
    }

    @Transactional
    public ApiResponse createCartItem(CartItem cartItem){
        CartItem savedCartItem = cartItemRepository.save(cartItem);

        CartItemDTO cartItemDTO = DtoConverter.toCartItemDTO(savedCartItem);

        ApiResponse response = new ApiResponse();
        response.setStatusCode(201);
        response.setMessage("Cart item created successfully");
        response.setCartItem(cartItemDTO);
        return response;
    }

//
//    // Method to create a new cart item
//    @Transactional
//    public ApiResponse createCartItem(CartItem cartItem) {
//        CartItem savedCartItem = cartItemRepository.save(cartItem);
//        ApiResponse response = new ApiResponse();
//        response.setStatusCode(201);
//        response.setMessage("Cart item created successfully");
//        response.setCartItem(savedCartItem);
//        return response;
//    }
//
//    // Method to update an existing cart item
//    @Transactional
//    public ApiResponse updateCartItem(Long id, CartItem cartItemDetails) {
//        CartItem cartItem = cartItemRepository.findById(id)
//                .orElseThrow(() -> new RuntimeException("Cart item not found with id " + id));
//        // Update fields as needed
//        // For example:
//        cartItem.setQuantity(cartItemDetails.getQuantity());
//        CartItem updatedCartItem = cartItemRepository.save(cartItem);
//        ApiResponse response = new ApiResponse();
//        response.setStatusCode(200);
//        response.setMessage("Cart item updated successfully");
//        response.setCartItem(updatedCartItem);
//        return response;
//    }
//
//    // Method to delete a cart item by ID
//    @Transactional
//    public ApiResponse deleteCartItem(Long id) {
//        CartItem cartItem = cartItemRepository.findById(id)
//                .orElseThrow(() -> new RuntimeException("Cart item not found with id " + id));
//        cartItemRepository.delete(cartItem);
//        ApiResponse response = new ApiResponse();
//        response.setStatusCode(204);
//        response.setMessage("Cart item deleted successfully");
//        return response;
//    }
}
