package com.example.controller;

import java.util.ArrayList;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.model.Order;
import com.example.service.OrderService;

@RestController
@RequestMapping("/order")
public class OrderController {
    
    @Autowired
    OrderService orderService;

    @PostMapping("/")
    public void addOrder(@RequestBody Order order){
        orderService.addOrder(order);
    }

    @GetMapping("/{orderId}")
    public Order getOrderById(@PathVariable UUID orderId){
        return orderService.getOrderById(orderId);
    }

    @GetMapping("/")
    public ArrayList<Order> getOrders(){
        return orderService.getOrders();
    }

    @DeleteMapping("/delete/{orderId}")
    public String deleteOrderById(@PathVariable UUID orderId){
    orderService.deleteOrderById(orderId);
    return "Order deleted successfully";
}
}
