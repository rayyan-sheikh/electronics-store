package com.rayyan.backend.dto;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.rayyan.backend.entity.Product;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CartItemDTO {

    private long id;
    private long cartId;
    private int quantity;
    private long productId;
}
