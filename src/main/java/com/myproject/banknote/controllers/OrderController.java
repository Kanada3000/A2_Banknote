package com.myproject.banknote.controllers;

import java.util.List;
import java.util.UUID;

import com.myproject.banknote.entities.Banknote;
import com.myproject.banknote.entities.Customer;
import com.myproject.banknote.entities.Order;
import com.myproject.banknote.entities.dto.NewOrderDTO;
import com.myproject.banknote.services.OrderService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javassist.NotFoundException;

@RestController
@RequestMapping("order")
public class OrderController {
    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public ResponseEntity<Order> create(@RequestBody NewOrderDTO newOrder) {
        Customer customer = newOrder.getCustomer();
        customer.setId(UUID.randomUUID());
        List<Banknote> banknote = newOrder.getBanknote();
        orderService.addOrder(banknote, customer);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<List<Order>> show() {
        return ResponseEntity.ok(orderService.getAllOrders());
    }

    @GetMapping("{id}")
    public ResponseEntity<Order> showById(@PathVariable UUID id) throws NotFoundException {
        return ResponseEntity.ok(orderService.getOrderById(id));
    }
}
