package com.rayyan.backend.controller;

import com.rayyan.backend.entity.Address;
import com.rayyan.backend.service.AddressService;
import com.rayyan.backend.utils.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/addresses")
public class AddressController {

    @Autowired
    private AddressService addressService;

    @GetMapping
    public ResponseEntity<ApiResponse> getAllAddresses() {
        ApiResponse response = addressService.getAllAddresses();
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> getAddressById(@PathVariable Long id) {
        ApiResponse response = addressService.getAddressById(id);
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }

    @PostMapping
    public ResponseEntity<ApiResponse> createAddress(@RequestBody Address address) {
        ApiResponse response = addressService.createAddress(address);
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> updateAddress(@PathVariable Long id, @RequestBody Address addressDetails) {
        ApiResponse response = addressService.updateAddress(addressDetails);
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteAddress(@PathVariable Long id) {
        ApiResponse response = addressService.deleteAddress(id);
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }
}
