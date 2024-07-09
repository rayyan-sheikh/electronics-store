package com.rayyan.backend.dto;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.rayyan.backend.entity.CartItem;
import com.rayyan.backend.entity.Category;
import com.rayyan.backend.entity.OrderItem;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductDTO {

    private long id;
    private String name;
    private String description;
    private String imgUrl1;
    private String imgUrl2;
    private String imgUrl3;
    private  double price;
    private long categoryId;
    private List<OrderItemDTO> orderItems = new ArrayList<>();
    private List<CartItemDTO> cartItems = new ArrayList<>();
}
