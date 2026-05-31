package com.adix.OrderService.service;

import com.adix.OrderService.event.OrderEvent;
import com.adix.OrderService.kafka.OrderProducer;
import com.adix.OrderService.model.Order;
import com.adix.OrderService.model.OrderStatus;
import com.adix.OrderService.repository.IOrderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
@Service
public class OrderService implements IOrderSevice {
    @Autowired
    private IOrderRepo repo;

    @Autowired
    private OrderProducer producer;

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
        OrderEvent oe=new OrderEvent(o.getId(),o.getCustId(),o.getStatus(),o.getTotalAmount(),LocalDateTime.now(),"ORDER_CONFIRMED");
        o.setId(null);
        producer.sendOrderEvent(oe);
        return repo.save(o);
    }

    @Override
    public Order updateOrder(Order o) {
        OrderEvent oe=new OrderEvent(o.getId(),o.getCustId(),o.getStatus(),o.getTotalAmount(),LocalDateTime.now(),"ORDER_UPDATED" );
        producer.sendOrderEvent(oe);
        return repo.save(o);
    }

    @Override
    public Long cancelOrder(Long id) {
        Order o=repo.findById(id).orElseThrow(() -> new RuntimeException("Order not found"));
        o.setStatus(OrderStatus.CANCELLED);
        repo.save(o);
        OrderEvent event= new OrderEvent(o.getId(),
                o.getCustId(),o.getStatus(),o.getTotalAmount(), LocalDateTime.now(),"ORDER_CANCELLED");
        producer.sendOrderEvent(event);
        return repo.getReferenceById(id).getId();
    }

    @Override
    public String deliverOrder(Long id) {
        Order o=repo.findById(id).orElseThrow(()->new RuntimeException("Order not found"));
        o.setStatus(OrderStatus.DELIVERED);
        repo.save(o);

        OrderEvent event= new OrderEvent(o.getId(),
                o.getCustId(),o.getStatus(),o.getTotalAmount(), LocalDateTime.now(),"ORDER_DELIVERED");
        producer.sendOrderEvent(event);

        return "Order id " +id+ " is delivered";
    }
}
