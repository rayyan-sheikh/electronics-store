package com.rayyan.backend.service;


import com.rayyan.backend.dto.ProductDTO;
import com.rayyan.backend.entity.Product;
import com.rayyan.backend.repository.ProductRepository;
import com.rayyan.backend.utils.ApiResponse;
import com.rayyan.backend.utils.DtoConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;

    // Method to get all products
    public ApiResponse getAllProducts() {
        List<Product> products = productRepository.findAll();
        List<ProductDTO> productDTOS = products.stream()
                .map(DtoConverter::toProductDTO)
                .collect(Collectors.toList());
        ApiResponse response = new ApiResponse();
        response.setStatusCode(200);
        response.setMessage("Products retrieved successfully");
        response.setProductList(productDTOS);
        return response;
    }

    // Method to get a product by ID
    public ApiResponse getProductById(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Product not found with id " +id));

        ProductDTO productDTO = DtoConverter.toProductDTO(product);
        ApiResponse response = new ApiResponse();

        response.setStatusCode(200);
        response.setMessage("Product retrieved successfully");
        response.setProduct(productDTO);
        return response;
    }

    // Method to create a new product
    @Transactional
    public ApiResponse createProduct(Product product) {
        Product savedProduct = productRepository.save(product);
        ProductDTO productDTO = DtoConverter.toProductDTO(product);
        ApiResponse response = new ApiResponse();
        response.setStatusCode(201);
        response.setMessage("Product created successfully");
        response.setProduct(productDTO);
        return response;
    }

    public ApiResponse searchProductsByName(String name) {
        List<Product> products = productRepository.findByNameContainingIgnoreCase(name);
        ApiResponse response = new ApiResponse();

        if (products.isEmpty()) {
            response.setStatusCode(404);
            response.setMessage("No products found with the given name");
            response.setProductList(Collections.emptyList());
        } else {
            List<ProductDTO> productDTOS = products.stream()
                    .map(DtoConverter::toProductDTO)
                    .collect(Collectors.toList());
            response.setStatusCode(200);
            response.setMessage("Product searched successfully");
            response.setProductList(productDTOS);
        }

        return response;
    }


    public ApiResponse getProductsByCategory(Long categoryId) {
        List<Product> products = productRepository.findByCategoryId(categoryId);
        List<ProductDTO> productDTOS = products.stream()
                .map(DtoConverter::toProductDTO)
                .collect(Collectors.toList());

        ApiResponse response = new ApiResponse();
        response.setStatusCode(200);
        response.setMessage("Products retrieved successfully");
        response.setProductList(productDTOS);
        return response;

    }
//
//    // Method to update an existing product
//    @Transactional
//    public ApiResponse updateProduct(Long id, Product productDetails) {
//        Product product = productRepository.findById(id)
//                .orElseThrow(() -> new RuntimeException("Product not found with id " + id));
//        product.setName(productDetails.getName());
//        product.setDescription(productDetails.getDescription());
//        // Set other fields as needed
//        Product updatedProduct = productRepository.save(product);
//        ApiResponse response = new ApiResponse();
//        response.setStatusCode(200);
//        response.setMessage("Product updated successfully");
//        response.setProduct(updatedProduct);
//        return response;
//    }
//
//    // Method to delete a product by ID
//    @Transactional
//    public ApiResponse deleteProduct(Long id) {
//        Product product = productRepository.findById(id)
//                .orElseThrow(() -> new RuntimeException("Product not found with id " + id));
//        productRepository.delete(product);
//        ApiResponse response = new ApiResponse();
//        response.setStatusCode(204);
//        response.setMessage("Product deleted successfully");
//        return response;
//    }


}
