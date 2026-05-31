package com.adix.OrderService.controller;

import com.adix.OrderService.model.Order;
import com.adix.OrderService.service.IOrderSevice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class OrderController {
    @Autowired
    private IOrderSevice serviceRepo;

    @PostMapping("/addToCart")
    public Order placeOrder(@RequestBody Order o){
        return serviceRepo.placeOrder(o);
    }

    @GetMapping("/getOrderById/{id}")
    public Order getOrder(@PathVariable Long id){
        return serviceRepo.getOrderById(id);
    }

}
