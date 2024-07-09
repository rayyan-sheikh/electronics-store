package com.rayyan.backend.service;


import com.rayyan.backend.dto.OrderItemDTO;
import com.rayyan.backend.entity.OrderItem;
import com.rayyan.backend.repository.OrderItemRepository;
import com.rayyan.backend.utils.ApiResponse;
import com.rayyan.backend.utils.DtoConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderItemService {

    @Autowired
    private OrderItemRepository orderItemRepository;

    public ApiResponse getAllOrderItems(){
        List<OrderItem> orderItems = orderItemRepository.findAll();
        List<OrderItemDTO> orderItemDTOs = orderItems.stream()
                .map(DtoConverter::toOrderItemDTO)
                .collect(Collectors.toList());
        ApiResponse response = new ApiResponse();
        response.setStatusCode(200);
        response.setMessage("Order items retrieved successfully");
        response.setOrderItemList(orderItemDTOs);
        return response;
    }

    public ApiResponse getOrderItemById(Long id){
        OrderItem orderItem = orderItemRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Order Item not found with id " + id));
        OrderItemDTO orderItemDTO = DtoConverter.toOrderItemDTO(orderItem);

        ApiResponse response = new ApiResponse();
        response.setStatusCode(200);
        response.setMessage("Order item retrieved successfully");
        response.setOrderItem(orderItemDTO);
        return response;
    }

    @Transactional
    public ApiResponse createOrderItem(OrderItem orderItem){
        OrderItem savedOrderItem = orderItemRepository.save(orderItem);
        OrderItemDTO orderItemDTO = DtoConverter.toOrderItemDTO(savedOrderItem);

        ApiResponse response = new ApiResponse();
        response.setStatusCode(201);
        response.setMessage("Order item created successfully");
        response.setOrderItem(orderItemDTO);
        return response;
    }



//
//    // Method to update an existing order item
//    @Transactional
//    public ApiResponse updateOrderItem(Long id, OrderItem orderItemDetails) {
//        OrderItem orderItem = orderItemRepository.findById(id)
//                .orElseThrow(() -> new RuntimeException("Order item not found with id " + id));
//
//         orderItem.setQuantity(orderItemDetails.getQuantity());
//         orderItem.setProduct(orderItemDetails.getProduct());
//        OrderItem updatedOrderItem = orderItemRepository.save(orderItem);
//        ApiResponse response = new ApiResponse();
//        response.setStatusCode(200);
//        response.setMessage("Order item updated successfully");
//        response.setOrderItem(updatedOrderItem);
//        return response;
//    }
//
//    // Method to delete an order item by ID
//    @Transactional
//    public ApiResponse deleteOrderItem(Long id) {
//        OrderItem orderItem = orderItemRepository.findById(id)
//                .orElseThrow(() -> new RuntimeException("Order item not found with id " + id));
//        orderItemRepository.delete(orderItem);
//        ApiResponse response = new ApiResponse();
//        response.setStatusCode(204);
//        response.setMessage("Order item deleted successfully");
//        return response;
//    }
}
