package com.rayyan.backend.controller;

import com.rayyan.backend.entity.Order;
import com.rayyan.backend.service.OrderService;
import com.rayyan.backend.utils.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping
    public ResponseEntity<ApiResponse> getAllOrders() {
        ApiResponse response = orderService.getAllOrders();
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> getOrderById(@PathVariable Long id) {
        ApiResponse response = orderService.getOrderById(id);
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }

    @PostMapping
    public ResponseEntity<ApiResponse> createOrder(@RequestBody Order order) {
        ApiResponse response = orderService.createOrder(order);
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }

//    @PutMapping("/{id}")
//    public ResponseEntity<ApiResponse> updateOrder(@PathVariable Long id, @RequestBody Order orderDetails) {
//        ApiResponse response = orderService.updateOrder(id, orderDetails);
//        return ResponseEntity.status(response.getStatusCode()).body(response);
//    }
//
//    @DeleteMapping("/{id}")
//    public ResponseEntity<ApiResponse> deleteOrder(@PathVariable Long id) {
//        ApiResponse response = orderService.deleteOrder(id);
//        return ResponseEntity.status(response.getStatusCode()).body(response);
//    }
}
