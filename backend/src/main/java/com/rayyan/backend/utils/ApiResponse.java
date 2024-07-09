package com.rayyan.backend.utils;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.rayyan.backend.dto.*;
import com.rayyan.backend.entity.*;
import lombok.Data;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResponse {

    private int statusCode;
    private String message;

    private String token;
    private String role;
    private String expirationTime;
    private String bookingConfirmationCode;

    private UserDTO user;
    private AddressDTO address;
    private CartDTO cart;
    private CartItemDTO cartItem;
    private CategoryDTO category;
    private OrderDTO order;
    private OrderItemDTO orderItem;
    private PaymentDTO payment;
    private ProductDTO product;


    private List<UserDTO> userList;
    private List<AddressDTO> addressList;
    private List<CartDTO> cartList;
    private List<CartItemDTO> cartItemList;
    private List<CategoryDTO> categoryList;
    private List<OrderDTO> orderList;
    private List<OrderItemDTO> orderItemList;
    private List<PaymentDTO> paymentList;
    private List<ProductDTO> productList;
}

