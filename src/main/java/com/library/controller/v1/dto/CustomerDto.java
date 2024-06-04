package com.library.controller.v1.dto;

import com.library.entity.Customer;

public record CustomerDto(String fullName, String phoneNumber, String email, String address) {

    public Customer toCustomer(){
        return new Customer(fullName, phoneNumber, email, address);
    }
}
