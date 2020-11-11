package com.myproject.banknote.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.myproject.banknote.entities.Customer;
import com.myproject.banknote.repo.CustomerRepository;

import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CustomerService {
    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Transactional
    public Customer addCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    @Transactional
    public List<Customer> getAllCustomer() {
        return customerRepository.findAll();
    }

    @Transactional
    public Customer getCustomerById(UUID id) throws NotFoundException {
        Optional<Customer> customer = customerRepository.findById(id);
        if (customer.isPresent())
            return customer.get();
        else
            throw new NotFoundException(String.format("Customer with id %s wasn`t found", id));
    }

    @Transactional
    public void deleteCustomerById(UUID id) throws NotFoundException {
        customerRepository.delete(getCustomerById(id));
    }
}
