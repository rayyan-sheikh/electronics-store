package com.rayyan.backend.controller;


import com.rayyan.backend.entity.Category;
import com.rayyan.backend.service.CategoryService;
import com.rayyan.backend.utils.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public ResponseEntity<ApiResponse> getAllCategories() {
        ApiResponse response = categoryService.getAllCategories();
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> getCategoryById(@PathVariable Long id) {
        ApiResponse response = categoryService.getCategoryById(id);
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }

    @PostMapping
    public ResponseEntity<ApiResponse> createCategory(@RequestBody Category category) {
        ApiResponse response = categoryService.createCategory(category);
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }

//    @PutMapping("/{id}")
//    public ResponseEntity<ApiResponse> updateCategory(@PathVariable Long id, @RequestBody Category categoryDetails) {
//        ApiResponse response = categoryService.updateCategory(id, categoryDetails);
//        return ResponseEntity.status(response.getStatusCode()).body(response);
//    }

//    @DeleteMapping("/{id}")
//    public ResponseEntity<ApiResponse> deleteCategory(@PathVariable Long id) {
//        ApiResponse response = categoryService.deleteCategory(id);
//        return ResponseEntity.status(response.getStatusCode()).body(response);
//    }

}
