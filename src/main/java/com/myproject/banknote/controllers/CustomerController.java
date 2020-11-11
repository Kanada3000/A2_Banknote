package com.myproject.banknote.controllers;

import java.util.List;
import java.util.UUID;

import com.myproject.banknote.entities.Customer;
import com.myproject.banknote.entities.dto.CustomerDTO;
import com.myproject.banknote.services.CustomerService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javassist.NotFoundException;

@RestController
@RequestMapping("customer")
public class CustomerController {
    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    public ResponseEntity<List<Customer>> show() {
        return ResponseEntity.ok(customerService.getAllCustomer());
    }

    @GetMapping("{id}")
    public ResponseEntity<Customer> showById(@PathVariable UUID id) throws NotFoundException {
        return ResponseEntity.ok(customerService.getCustomerById(id));
    }

    @PostMapping
    public ResponseEntity<Customer> create(@RequestBody CustomerDTO dto) {
        Customer newCustomer = new Customer(dto.getName(), dto.getSurname(), dto.getBank());
        return ResponseEntity.ok(customerService.addCustomer(newCustomer));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) throws NotFoundException {
        customerService.deleteCustomerById(id);
        return ResponseEntity.noContent().build();
    }
}
