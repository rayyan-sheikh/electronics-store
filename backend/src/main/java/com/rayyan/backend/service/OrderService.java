package com.rayyan.backend.service;


import com.rayyan.backend.dto.OrderDTO;
import com.rayyan.backend.entity.Order;
import com.rayyan.backend.repository.OrderRepository;
import com.rayyan.backend.utils.ApiResponse;
import com.rayyan.backend.utils.DtoConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    public ApiResponse getAllOrders(){
        List<Order> orders = orderRepository.findAll();
        List<OrderDTO> orderDTOs = orders.stream()
                .map(DtoConverter::toOrderDTO)
                .collect(Collectors.toList());

        ApiResponse response = new ApiResponse();
        response.setStatusCode(200);
        response.setMessage("Orders retrieved successfully");
        response.setOrderList(orderDTOs);
        return response;
    }

    public ApiResponse getOrderById(Long id){
        Order order = orderRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Order not found with id " +id));

        OrderDTO orderDTO = DtoConverter.toOrderDTO(order);

        ApiResponse response = new ApiResponse();
        response.setStatusCode(200);
        response.setMessage("Order retrieved successfully");
        response.setOrder(orderDTO);

        return response;
    }

    public ApiResponse createOrder(Order order){
        Order savedOrder = orderRepository.save(order);

        OrderDTO orderDTO = DtoConverter.toOrderDTO(savedOrder);

        ApiResponse response = new ApiResponse();
        response.setStatusCode(201);
        response.setMessage("Order created successfully");
        response.setOrder(orderDTO);
        return response;
    }



//
//    // Method to update an existing order
//    @Transactional
//    public ApiResponse updateOrder(Long id, Order orderDetails) {
//        Order order = orderRepository.findById(id)
//                .orElseThrow(() -> new RuntimeException("Order not found with id " + id));
//        // Update fields as needed
//        // For example:
//        // order.setOrderDate(orderDetails.getOrderDate());
//        // order.setTotalAmount(orderDetails.getTotalAmount());
//        Order updatedOrder = orderRepository.save(order);
//        ApiResponse response = new ApiResponse();
//        response.setStatusCode(200);
//        response.setMessage("Order updated successfully");
//        response.setOrder(updatedOrder);
//        return response;
//    }
//
//    // Method to delete an order by ID
//    @Transactional
//    public ApiResponse deleteOrder(Long id) {
//        Order order = orderRepository.findById(id)
//                .orElseThrow(() -> new RuntimeException("Order not found with id " + id));
//        orderRepository.delete(order);
//        ApiResponse response = new ApiResponse();
//        response.setStatusCode(204);
//        response.setMessage("Order deleted successfully");
//        return response;
//    }


}
