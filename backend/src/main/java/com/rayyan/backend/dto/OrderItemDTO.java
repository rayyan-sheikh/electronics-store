package com.rayyan.backend.dto;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.rayyan.backend.entity.Order;
import com.rayyan.backend.entity.Product;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrderItemDTO {
    private long id;
    private long orderId;
    private long productId;
    private int quantity;
}
