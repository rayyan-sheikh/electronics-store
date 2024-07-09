package com.rayyan.backend.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.rayyan.backend.entity.Address;
import com.rayyan.backend.entity.Cart;
import com.rayyan.backend.entity.Order;
import com.rayyan.backend.entity.Payment;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDTO {

    private long id;
    private String email;
    private String name;
    private String phoneNumber;
    private String role;
    private AddressDTO address;

    private List<PaymentDTO> payments = new ArrayList<>();
    private List<CartDTO> carts = new ArrayList<>();
    private List<OrderDTO> orders = new ArrayList<>();
}
