package com.rayyan.backend.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "categories")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank(message = "Category name is required.")
    private String name;

    @NotBlank
    private String description;

    @NotBlank
    private String imgUrl;

    @OneToMany(mappedBy = "category")
    private List<Product> products = new ArrayList<>();
}
