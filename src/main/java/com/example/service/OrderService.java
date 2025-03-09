package com.example.service;

import java.util.ArrayList;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.model.Order;
import com.example.repository.OrderRepository;

@Service
@SuppressWarnings("rawtypes")
public class OrderService extends MainService<Order> {

    @Autowired
    OrderRepository orderRepository;

    public OrderService() {
    }

    public void addOrder(Order order) {
        orderRepository.save(order);
    }

    public ArrayList<Order> getOrders(){
        return orderRepository.findAll();
    }

    public Order getOrderById(UUID orderId){
        return orderRepository.getOrderById(orderId);
    }

    public void deleteOrderById(UUID orderId) throws IllegalArgumentException{
        orderRepository.deleteOrderById(orderId);
    }
    
}
