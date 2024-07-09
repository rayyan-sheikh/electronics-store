package com.rayyan.backend.utils;

import com.rayyan.backend.dto.*;
import com.rayyan.backend.entity.*;

import java.util.List;
import java.util.stream.Collectors;

public class DtoConverter {

    public static UserDTO toUserDTO(User user) {
        if (user == null) {
            return null;
        }

        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setEmail(user.getEmail());
        userDTO.setName(user.getName());
        userDTO.setPhoneNumber(user.getPhoneNumber());
        userDTO.setRole(user.getRole());
        userDTO.setAddress(DtoConverter.toAddressDTO(user.getAddress()));
        userDTO.setPayments(user.getPayments().stream()
                .map(DtoConverter::toPaymentDTO)
                .collect(Collectors.toList()));
        userDTO.setCarts(user.getCarts().stream()
                .map(cart -> {
                    CartDTO cartDTO = toCartDTO(cart);
                    cartDTO.setUserEmail(user.getEmail());
                    return cartDTO;
                })
                .collect(Collectors.toList()));
        userDTO.setOrders(user.getOrders().stream()
                .map(DtoConverter::toOrderDTO)
                .collect(Collectors.toList()));

        return userDTO;
    }

    public static CartDTO toCartDTO(Cart cart) {
        CartDTO cartDTO = new CartDTO();

        cartDTO.setId(cart.getId());
        cartDTO.setUserEmail(cart.getUser().getEmail());
//        cartDTO.setUser(DtoConverter.toUserDTO(cart.getUser()));
        if (cart.getCartItems() !=null){
            cartDTO.setCartItems(cart.getCartItems().stream()
                    .map(DtoConverter::toCartItemDTO)
                    .collect(Collectors.toList()));
        }

        return cartDTO;
    }

    public static AddressDTO toAddressDTO(Address address) {
        if (address == null) {
            return null;
        }

        AddressDTO addressDTO = new AddressDTO();
        addressDTO.setId(address.getId());
        addressDTO.setAddressLine1(address.getAddressLine1());
        addressDTO.setAddressLine2(address.getAddressLine2());
        addressDTO.setCity(address.getCity());
        addressDTO.setCountry(address.getCountry());
        if (address.getUser() != null) {
            addressDTO.setBelongsTo(address.getUser().getEmail());
        }

        return addressDTO;
    }

    public static PaymentDTO toPaymentDTO(Payment payment) {

        if (payment == null) {
            return null;
        }

        PaymentDTO paymentDTO = new PaymentDTO();
        paymentDTO.setId(payment.getId());
        paymentDTO.setAmount(payment.getAmount());
        paymentDTO.setOrder(DtoConverter.toOrderDTO(payment.getOrder()));
        paymentDTO.setUser(DtoConverter.toUserDTO(payment.getUser()));


        return paymentDTO;
    }

    public static OrderDTO toOrderDTO(Order order) {
        if (order == null) {
            return null;
        }
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setId(order.getId());
        orderDTO.setUserEmail(order.getUser().getEmail());
        orderDTO.setAddress(DtoConverter.toAddressDTO(order.getAddress()));
        orderDTO.setPaymentId(order.getPayment().getId());

        if (order.getOrderItems() != null) {
            orderDTO.setOrderItems(order.getOrderItems().stream()
                    .map(DtoConverter::toOrderItemDTO)
                    .collect(Collectors.toList())
            );
        }

        return orderDTO;
    }

    public static OrderItemDTO toOrderItemDTO(OrderItem orderItem){
        OrderItemDTO orderItemDTO = new OrderItemDTO();

        orderItemDTO.setId(orderItem.getId());
        orderItemDTO.setQuantity(orderItem.getQuantity());
        if (orderItem.getProduct() != null){
            orderItemDTO.setProductId(orderItem.getProduct().getId());
        }

        if (orderItem.getOrder() != null){
            orderItemDTO.setOrderId(orderItem.getOrder().getId());
        }



        return orderItemDTO;
    }

    public static ProductDTO toProductDTO(Product product){

        ProductDTO productDTO = new ProductDTO();

        productDTO.setId(product.getId());
        productDTO.setName(product.getName());
        productDTO.setDescription(product.getDescription());
        productDTO.setImgUrl1(product.getImgUrl1());
        productDTO.setImgUrl2(product.getImgUrl2());
        productDTO.setImgUrl3(product.getImgUrl3());
        productDTO.setPrice(product.getPrice());

        productDTO.setCategoryId(product.getCategory().getId());

        if(product.getOrderItems() != null){
            productDTO.setOrderItems(product.getOrderItems().stream()
                    .map(DtoConverter::toOrderItemDTO)
                    .collect(Collectors.toList()));
        }

        if(product.getCartItems() != null){
            productDTO.setCartItems(product.getCartItems().stream()
                    .map(DtoConverter::toCartItemDTO)
                    .collect(Collectors.toList()));
        }

        return productDTO;

    }

    public static CartItemDTO toCartItemDTO(CartItem cartItem){

        CartItemDTO cartItemDTO = new CartItemDTO();

        cartItemDTO.setId(cartItem.getId());
        cartItemDTO.setQuantity(cartItem.getQuantity());
        cartItemDTO.setCartId(cartItem.getCart().getId());
        cartItemDTO.setProductId(cartItem.getProduct().getId());

        return cartItemDTO;
    }


    public  static CategoryDTO toCategoryDTO(Category category){
        CategoryDTO categoryDTO = new CategoryDTO();

        categoryDTO.setId(category.getId());
        categoryDTO.setName(category.getName());
        categoryDTO.setDescription(category.getDescription());
        categoryDTO.setImgUrl(category.getImgUrl());

        if (category.getProducts() != null){
            categoryDTO.setProducts(category.getProducts().stream()
                    .map(DtoConverter::toProductDTO)
                    .collect(Collectors.toList()));
        }

        return categoryDTO;

    }


}
