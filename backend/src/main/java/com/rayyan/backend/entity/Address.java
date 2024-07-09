package com.rayyan.backend.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NonNull;

@Data
@Entity
@Table(name = "addresses")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank(message = "Address Line 1 can't be empty")
    private String addressLine1;

    @NotBlank(message = "Address Line 2 can't be empty")
    private String addressLine2;

    @NotBlank(message = "City can't be empty")
    private String city;

    @NotBlank(message = "Country can't be empty")
    private String country;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @OneToOne(mappedBy = "address", cascade = CascadeType.ALL)
    private Order order;
}
