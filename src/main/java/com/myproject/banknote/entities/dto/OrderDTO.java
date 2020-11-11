package com.myproject.banknote.entities.dto;

import java.util.List;

import com.myproject.banknote.entities.Order;

public class OrderDTO {
    private List<Order> order;

    public OrderDTO() {
    }

    public OrderDTO(List<Order> order) {
        this.order = order;
    }

    public List<Order> getOrder() {
        return order;
    }

    public void setOrder(List<Order> order) {
        this.order = order;
    }
}
