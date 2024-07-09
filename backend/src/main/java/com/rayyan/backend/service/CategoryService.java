package com.rayyan.backend.service;

import com.rayyan.backend.dto.AddressDTO;
import com.rayyan.backend.dto.CategoryDTO;
import com.rayyan.backend.entity.Address;
import com.rayyan.backend.entity.Category;
import com.rayyan.backend.repository.CategoryRepository;
import com.rayyan.backend.utils.ApiResponse;
import com.rayyan.backend.utils.DtoConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public ApiResponse getAllCategories() {
        List<Category> categories = categoryRepository.findAll();

        List<CategoryDTO> categoryDTOS = categories.stream()
                .map(DtoConverter::toCategoryDTO)
                .collect(Collectors.toList());

        ApiResponse response = new ApiResponse();
        response.setStatusCode(200);
        response.setMessage("Categories retrieved successfully");
        response.setCategoryList(categoryDTOS);
        return response;
    }

    public ApiResponse getCategoryById(Long id){
       Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found with id " + id));

        CategoryDTO categoryDTO = DtoConverter.toCategoryDTO(category);

        ApiResponse response = new ApiResponse();
        response.setStatusCode(200);
        response.setMessage("Category retrieved successfully");
        response.setCategory(categoryDTO);

        return response;
    }

    @Transactional
    public ApiResponse createCategory(Category category){
        Category savedCategory = categoryRepository.save(category);
        CategoryDTO categoryDTO  = DtoConverter.toCategoryDTO(savedCategory);

        ApiResponse response = new ApiResponse();
        response.setStatusCode(201);
        response.setMessage("Category created successfully");
        response.setCategory(categoryDTO);

        return response;
    }


//    // Method to update an existing category
//    @Transactional
//    public ApiResponse updateCategory(Long id, Category categoryDetails) {
//        Category category = categoryRepository.findById(id)
//                .orElseThrow(() -> new RuntimeException("Category not found with id " + id));
//        category.setName(categoryDetails.getName());
//        category.setDescription(categoryDetails.getDescription());
//        // Set other fields as needed
//        Category updatedCategory = categoryRepository.save(category);
//        ApiResponse response = new ApiResponse();
//        response.setStatusCode(200);
//        response.setMessage("Category updated successfully");
//        response.setCategory(updatedCategory);
//        return response;
//    }
//
//    // Method to delete a category by ID
//    @Transactional
//    public ApiResponse deleteCategory(Long id) {
//        Category category = categoryRepository.findById(id)
//                .orElseThrow(() -> new RuntimeException("Category not found with id " + id));
//        categoryRepository.delete(category);
//        ApiResponse response = new ApiResponse();
//        response.setStatusCode(204);
//        response.setMessage("Category deleted successfully");
//        return response;
//    }

}
