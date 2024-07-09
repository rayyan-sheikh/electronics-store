package com.rayyan.backend.dto;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.rayyan.backend.entity.CartItem;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CartDTO {

    private long id;
    private String userEmail;
//    private UserDTO user;
    private List<CartItemDTO> cartItems = new ArrayList<>();
}
