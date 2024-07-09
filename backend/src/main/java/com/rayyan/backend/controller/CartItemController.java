package com.rayyan.backend.controller;

import com.rayyan.backend.entity.CartItem;
import com.rayyan.backend.service.CartItemService;
import com.rayyan.backend.utils.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cart-items")
public class CartItemController {

    @Autowired
    private CartItemService cartItemService;

    @GetMapping
    public ResponseEntity<ApiResponse> getAllCartItems() {
        ApiResponse response = cartItemService.getAllCartItems();
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> getCartItemById(@PathVariable Long id) {
        ApiResponse response = cartItemService.getCartItemById(id);
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }

    @PostMapping
    public ResponseEntity<ApiResponse> createCartItem(@RequestBody CartItem cartItem) {
        ApiResponse response = cartItemService.createCartItem(cartItem);
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }
//
//    @PutMapping("/{id}")
//    public ResponseEntity<ApiResponse> updateCartItem(@PathVariable Long id, @RequestBody CartItem cartItemDetails) {
//        ApiResponse response = cartItemService.updateCartItem(id, cartItemDetails);
//        return ResponseEntity.status(response.getStatusCode()).body(response);
//    }
//
//    @DeleteMapping("/{id}")
//    public ResponseEntity<ApiResponse> deleteCartItem(@PathVariable Long id) {
//        ApiResponse response = cartItemService.deleteCartItem(id);
//        return ResponseEntity.status(response.getStatusCode()).body(response);
//    }
}
