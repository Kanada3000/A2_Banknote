package com.myproject.banknote.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.myproject.banknote.entities.Banknote;
import com.myproject.banknote.entities.Customer;
import com.myproject.banknote.entities.Order;
import com.myproject.banknote.entities.Storage;
import com.myproject.banknote.repo.BanknoteRepository;
import com.myproject.banknote.repo.CustomerRepository;
import com.myproject.banknote.repo.OrderRepository;
import com.myproject.banknote.repo.StorageRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javassist.NotFoundException;

@Service
public class OrderService {
    private final BanknoteRepository banknoteRepository;
    private final OrderRepository orderRepository;
    private final CustomerRepository customerRepository;
    private final StorageRepository storageRepository;

    @Autowired
    public OrderService(BanknoteRepository banknoteRepository, OrderRepository orderRepository,
            CustomerRepository customerRepository, StorageRepository storageRepository) {
        this.banknoteRepository = banknoteRepository;
        this.orderRepository = orderRepository;
        this.customerRepository = customerRepository;
        this.storageRepository = storageRepository;
    }

    @Transactional(readOnly = true)
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    @Transactional
    public void addOrder(List<Banknote> orderBanknote, Customer customer) {
        List<Banknote> toOrder = new ArrayList<>();
        for (Banknote b : orderBanknote) {
            Banknote banknote = banknoteRepository.findBanknoteById(b.getId());
            Storage storageBanknote = storageRepository.findByBanknote(banknote);
            if (storageBanknote == null) {
                continue;
            }
            int banknoteQuantity = storageBanknote.getQuantity();
            if (banknoteQuantity > 0) {
                toOrder.add(banknote);
                int newQuantity = banknoteQuantity - 1;
                storageBanknote.setQuantity(newQuantity);
                storageRepository.save(storageBanknote);
            } else {
                storageRepository.delete(storageBanknote);
            }
        }
        if (toOrder.size() > 0) {
            Order order = new Order(toOrder, customer);
            order.setId(UUID.randomUUID());
            customerRepository.save(customer);
            orderRepository.save(order);
        }
    }

    @Transactional
    public Order getOrderById(UUID id) throws NotFoundException {
        Optional<Order> tempOrder = orderRepository.findById(id);
        if (tempOrder.isPresent())
            return tempOrder.get();
        else
            throw new NotFoundException(String.format("Order with id %s was not found", id));
    }
}
