package com.adix.OrderService.service;

import com.adix.OrderService.model.Order;
import com.adix.OrderService.model.OrderStatus;
import com.adix.OrderService.repository.IOrderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class OrderService implements IOrderSevice {
    @Autowired
    private IOrderRepo repo;

    @Override
    public Order getOrderById(Long id) {
        return repo.getReferenceById(id);
    }

    @Override
    public List<Order> getAllOrders() {
        return repo.findAll();
    }

    @Override
    public Order placeOrder(Order o) {
        o.setStatus(OrderStatus.CONFIRMED);
        return repo.save(o);
    }

    @Override
    public Order updateOrder(Order o) {
        return repo.save(o);
    }

    @Override
    public Long cancelOrder(Long id) {
        Order o=repo.findById(id).orElseThrow(() -> new RuntimeException("Order not found"));
        o.setStatus(OrderStatus.CANCELLED);
        repo.save(o);
        return repo.getReferenceById(id).getId();
    }

    @Override
    public String deliverOrder(Long id) {
        Order o=repo.findById(id).orElseThrow(()->new RuntimeException("Order not found"));
        o.setStatus(OrderStatus.DELIVERED);
        return "Order id " +id+ " is delivered";
    }
}
