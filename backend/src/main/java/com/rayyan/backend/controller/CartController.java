package com.rayyan.backend.controller;


import com.rayyan.backend.entity.Cart;
import com.rayyan.backend.service.CartService;
import com.rayyan.backend.utils.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/carts")
public class CartController {

    @Autowired
    private CartService cartService;

    @GetMapping
    public ApiResponse getAllCarts() {
        return cartService.getAllCarts();
    }

    @GetMapping("/{id}")
    public ApiResponse getCartById(@PathVariable Long id) {
        return cartService.getCartById(id);
    }

    @PostMapping
    public ApiResponse createCart(@RequestBody Cart cart) {
        return cartService.createCart(cart);
    }

//    @GetMapping
//    public ResponseEntity<ApiResponse> getAllCarts() {
//        ApiResponse response = cartService.getAllCarts();
//        return ResponseEntity.status(response.getStatusCode()).body(response);
//    }
//
//    @GetMapping("/{id}")
//    public ResponseEntity<ApiResponse> getCartById(@PathVariable Long id) {
//        ApiResponse response = cartService.getCartById(id);
//        return ResponseEntity.status(response.getStatusCode()).body(response);
//    }
//
//    @PostMapping
//    public ResponseEntity<ApiResponse> createCart(@RequestBody Cart cart) {
//        ApiResponse response = cartService.createCart(cart);
//        return ResponseEntity.status(response.getStatusCode()).body(response);
//    }
//
//    @PutMapping("/{id}")
//    public ResponseEntity<ApiResponse> updateCart(@PathVariable Long id, @RequestBody Cart cartDetails) {
//        ApiResponse response = cartService.updateCart(id, cartDetails);
//        return ResponseEntity.status(response.getStatusCode()).body(response);
//    }
//
//    @DeleteMapping("/{id}")
//    public ResponseEntity<ApiResponse> deleteCart(@PathVariable Long id) {
//        ApiResponse response = cartService.deleteCart(id);
//        return ResponseEntity.status(response.getStatusCode()).body(response);
//    }
}
