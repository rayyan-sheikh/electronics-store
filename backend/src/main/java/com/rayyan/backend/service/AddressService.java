package com.rayyan.backend.service;



import com.rayyan.backend.dto.AddressDTO;
import com.rayyan.backend.entity.Address;
import com.rayyan.backend.repository.AddressRepository;
import com.rayyan.backend.utils.ApiResponse;
import com.rayyan.backend.utils.DtoConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class AddressService {


    @Autowired
    private AddressRepository addressRepository;


    @Transactional(readOnly = true)
    public ApiResponse getAllAddresses() {
        List<Address> addresses = addressRepository.findAll();

        List<AddressDTO> addressDTOs = addresses.stream()
                .map(DtoConverter::toAddressDTO)
                .collect(Collectors.toList());

        ApiResponse response = new ApiResponse();
        response.setStatusCode(200);
        response.setMessage("Addresses retrieved successfully");
        response.setAddressList(addressDTOs);

        return response;
    }

    // Method to get an address by ID
    @Transactional(readOnly = true)
    public ApiResponse getAddressById(Long id) {
        Address address = addressRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Address not found with id " + id));

        AddressDTO addressDTO = DtoConverter.toAddressDTO(address);

        ApiResponse response = new ApiResponse();
        response.setStatusCode(200);
        response.setMessage("Address retrieved successfully");
        response.setAddress(addressDTO);

        return response;
    }

    // Method to create a new address
    @Transactional
    public ApiResponse createAddress(Address address) {
        // Assuming the User object is already set in the Address entity
        Address savedAddress = addressRepository.save(address);
        AddressDTO savedAddressDTO = DtoConverter.toAddressDTO(savedAddress);

        ApiResponse response = new ApiResponse();
        response.setStatusCode(201);
        response.setMessage("Address created successfully");
        response.setAddress(savedAddressDTO);

        return response;
    }


    // Method to update an existing address
    @Transactional
    public ApiResponse updateAddress(Address address) {
        Long id = address.getId();
        Address existingAddress = addressRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Address not found with id " + id));

        // Update the fields based on the provided Address object
        existingAddress.setAddressLine1(address.getAddressLine1());
        existingAddress.setCity(address.getCity());
        existingAddress.setCountry(address.getCountry());

        // If needed, update the User association
//        if (address.getUser() != null) {
//            existingAddress.setUser(address.getUser());
//        }

        Address updatedAddress = addressRepository.save(existingAddress);
        AddressDTO updatedAddressDTO = DtoConverter.toAddressDTO(updatedAddress);

        ApiResponse response = new ApiResponse();
        response.setStatusCode(200);
        response.setMessage("Address updated successfully");
        response.setAddress(updatedAddressDTO);

        return response;
    }


    // Method to delete an address by ID
    @Transactional
    public ApiResponse deleteAddress(Long id) {
        Address address = addressRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Address not found with id " + id));
        addressRepository.delete(address);
        ApiResponse response = new ApiResponse();
        response.setStatusCode(204);
        response.setMessage("Address deleted successfully");
        return response;
    }

}
