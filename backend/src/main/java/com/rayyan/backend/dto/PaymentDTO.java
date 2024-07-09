package com.rayyan.backend.dto;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.rayyan.backend.entity.Order;
import com.rayyan.backend.entity.User;
import lombok.Data;

import java.math.BigDecimal;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PaymentDTO {
    private long id;
    private OrderDTO order;
    private BigDecimal amount;
    private UserDTO user;
}
