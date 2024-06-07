package com.library.service;

import com.library.controller.v1.dto.CustomerDto;
import com.library.entity.Customer;
import com.library.exeptions.MissingDataException;
import com.library.exeptions.NotFoundException;
import com.library.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    private final CustomerRepository repository;

    public CustomerService(CustomerRepository repository) {
        this.repository = repository;
    }

    public Customer create(CustomerDto customerDto) {
        Customer newCustomer = customerDto.toCustomer();

        if (    (newCustomer.getFullName() == null || newCustomer.getFullName().isEmpty()) ||
                (newCustomer.getAddress() == null || newCustomer.getAddress().isEmpty()) ||
                (newCustomer.getEmail() == null || newCustomer.getEmail().isEmpty()) ||
                (newCustomer.getPhoneNumber() == null || newCustomer.getPhoneNumber().isEmpty())
        ) throw new MissingDataException("The request came with the mandatory customer data missing or empty, " +
                "please review the request. (Mandatory attributes: fullName, phoneNumber, email and address)");

        return repository.save(newCustomer);
    }

    public void delete(Long idCustomer) {
        Optional<Customer> customer = repository.findById(idCustomer);

        if (customer.isEmpty()) {
            throw new NotFoundException("This customer ID is not saved in our system. Please enter a valid ID");
        } else {
            repository.delete(customer.get());
        }
    }

    public Customer getByID(Long idCustomer) {
        Optional<Customer> customer = repository.findById(idCustomer);

        if (customer.isEmpty()) {
            throw new NotFoundException("There is no customer with this ID. Please try again with a valid ID");
        }
        return customer.get();
    }

    public Customer update(Long idCustomer, CustomerDto customerDto) {
        Optional<Customer> customer = repository.findById(idCustomer);

        if (customer.isEmpty()) {
            throw new NotFoundException("There is no customer with this ID. Please try again with a valid ID");
        }

        Customer oldCustomer = customer.get();
        Customer newCustomer = customerDto.toCustomer();

        if(newCustomer.getFullName() != null){
            if(newCustomer.getFullName().isEmpty()) throw new MissingDataException("The request came with the " +
                    "mandatory customer data missing or empty");
            else oldCustomer.setFullName(newCustomer.getFullName());
        }

        if(newCustomer.getPhoneNumber() != null){
            if(newCustomer.getPhoneNumber().isEmpty()) throw new MissingDataException("The request came with the " +
                    "mandatory customer data missing or empty");
            else oldCustomer.setPhoneNumber(newCustomer.getPhoneNumber());
        }

        if(newCustomer.getAddress() != null){
            if(newCustomer.getAddress().isEmpty()) throw new MissingDataException("The request came with the " +
                    "mandatory customer data missing or empty");
            else oldCustomer.setAddress(newCustomer.getAddress());
        }

        if(newCustomer.getEmail() != null){
            if(newCustomer.getEmail().isEmpty()) throw new MissingDataException("The request came with the " +
                    "mandatory customer data missing or empty");
            else oldCustomer.setEmail(newCustomer.getEmail());
        }

        return oldCustomer;
    }

    public List<Customer> getAll() {
        List<Customer> allCustomers = repository.findAll();
        return allCustomers;
    }
}
