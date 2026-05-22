package com.adix.OrderService.repository;

import com.adix.OrderService.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IOrderRepo extends JpaRepository<Order, Long> {

}
