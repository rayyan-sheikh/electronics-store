package com.rayyan.backend.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.rayyan.backend.entity.OrderItem;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrderDTO {

    private long id;
    private String userEmail;
//    private UserDTO user;
    private AddressDTO address;
    private long paymentId;
    private List<OrderItemDTO> orderItems = new ArrayList<>();
}
