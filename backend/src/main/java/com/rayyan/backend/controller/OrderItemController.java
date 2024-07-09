package com.rayyan.backend.controller;


import com.rayyan.backend.entity.OrderItem;
import com.rayyan.backend.service.OrderItemService;
import com.rayyan.backend.utils.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/order-items")
public class OrderItemController {

    @Autowired
    private OrderItemService orderItemService;

    @GetMapping
    public ResponseEntity<ApiResponse> getAllOrderItems() {
        ApiResponse response = orderItemService.getAllOrderItems();
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> getOrderItemById(@PathVariable Long id) {
        ApiResponse response = orderItemService.getOrderItemById(id);
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }

    @PostMapping
    public ResponseEntity<ApiResponse> createOrderItem(@RequestBody OrderItem orderItem) {
        ApiResponse response = orderItemService.createOrderItem(orderItem);
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }

//    @PutMapping("/{id}")
//    public ResponseEntity<ApiResponse> updateOrderItem(@PathVariable Long id, @RequestBody OrderItem orderItemDetails) {
//        ApiResponse response = orderItemService.updateOrderItem(id, orderItemDetails);
//        return ResponseEntity.status(response.getStatusCode()).body(response);
//    }
//
//    @DeleteMapping("/{id}")
//    public ResponseEntity<ApiResponse> deleteOrderItem(@PathVariable Long id) {
//        ApiResponse response = orderItemService.deleteOrderItem(id);
//        return ResponseEntity.status(response.getStatusCode()).body(response);
//    }
}
