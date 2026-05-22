package com.adix.OrderService.service;

import com.adix.OrderService.model.Order;

import java.util.List;

public interface IOrderSevice {
    public Order getOrderById(Long id);
    public List<Order> getAllOrders();
    public Order placeOrder(Order o);
    public Order updateOrder(Order o);
    public Long cancelOrder(Long id);
    public String deliverOrder(Long id);
}
