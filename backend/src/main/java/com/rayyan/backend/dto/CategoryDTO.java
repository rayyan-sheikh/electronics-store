package com.rayyan.backend.dto;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CategoryDTO {

    private long id;
    private String name;
    private String description;
    private String imgUrl;
    private List<ProductDTO> products = new ArrayList<>();
}
