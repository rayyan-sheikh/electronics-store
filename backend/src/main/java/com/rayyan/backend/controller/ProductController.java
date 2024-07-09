package com.rayyan.backend.controller;


import com.rayyan.backend.entity.Product;
import com.rayyan.backend.service.ProductService;
import com.rayyan.backend.utils.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping
    public ResponseEntity<ApiResponse> getAllProducts() {
        ApiResponse response = productService.getAllProducts();
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> getProductById(@PathVariable Long id) {
        ApiResponse response = productService.getProductById(id);
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }

    @PostMapping
    public ResponseEntity<ApiResponse> createProduct(@RequestBody Product product) {
        ApiResponse response = productService.createProduct(product);
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }

    @GetMapping("/search")
    public ResponseEntity<ApiResponse> searchProducts(@RequestParam String query) {
        ApiResponse response = productService.searchProductsByName(query);
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }

    @GetMapping("/category/{categoryId}")
    public ResponseEntity<ApiResponse> getProductsByCategory(@PathVariable Long categoryId){
        ApiResponse response = productService.getProductsByCategory(categoryId);
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }
//
//    @PutMapping("/{id}")
//    public ResponseEntity<ApiResponse> updateProduct(@PathVariable Long id, @RequestBody Product productDetails) {
//        ApiResponse response = productService.updateProduct(id, productDetails);
//        return ResponseEntity.status(response.getStatusCode()).body(response);
//    }
//
//    @DeleteMapping("/{id}")
//    public ResponseEntity<ApiResponse> deleteProduct(@PathVariable Long id) {
//        ApiResponse response = productService.deleteProduct(id);
//        return ResponseEntity.status(response.getStatusCode()).body(response);
//    }
}
