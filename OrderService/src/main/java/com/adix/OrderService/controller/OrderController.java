package com.adix.OrderService.controller;

import com.adix.OrderService.model.Order;
import com.adix.OrderService.service.IOrderSevice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {
    private IOrderSevice serviceRepo;

    @PostMapping("/addToCart")
    public Order placeOrder(@RequestBody Order o){
        return serviceRepo.placeOrder(o);
    }

    @GetMapping("/getOrderById")
    public Order getOrder(@RequestBody Long id){
        return serviceRepo.getOrderById(id);
    }

}
