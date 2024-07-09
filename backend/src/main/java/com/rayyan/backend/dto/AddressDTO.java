package com.rayyan.backend.dto;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AddressDTO {

    private long id;
    private String addressLine1;
    private String addressLine2;
    private String city;
    private String country;
    private String belongsTo;
}
