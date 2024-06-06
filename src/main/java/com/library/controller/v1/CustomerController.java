package com.library.controller.v1;

import com.library.controller.v1.dto.CustomerDto;
import com.library.entity.Customer;
import com.library.service.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController {
    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/{idCustomer}")
    public ResponseEntity<Customer> getCustomer(@PathVariable Long idCustomer) {
        Customer customer = customerService.getByID(idCustomer);
        return new ResponseEntity<>(customer, HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<List<Customer>> getAllCustomers() {
        List<Customer> all = customerService.getAll();
        return new ResponseEntity<>(all, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<Customer> createCustomer(@RequestBody CustomerDto customerDto) {
        Customer customer = customerService.create(customerDto);
        return new ResponseEntity<>(customer, HttpStatus.CREATED);
    }

    @PutMapping("/update/{idCustomer}")
    public ResponseEntity<Customer> updateCustomer(@PathVariable Long idCustomer, @RequestBody CustomerDto customerDto) {
        Customer customer = customerService.update(idCustomer, customerDto);
        return new ResponseEntity<>(customer, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{idCustomer}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable Long idCustomer) {
        customerService.delete(idCustomer);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
