package com.example.repository;

import java.util.ArrayList;
import java.util.UUID;

import org.springframework.stereotype.Repository;

import com.example.model.Order;

@Repository
public class OrderRepository extends MainRepository<Order> {   

    public OrderRepository() {
    }

    public void addOrder(Order order){
        save(order);
    }

    public ArrayList<Order> getOrders(){
       return findAll();
    }

    public Order getOrderById(UUID orderId){
        ArrayList<Order> orders = getOrders();
        for(Order order : orders){
            if(order.getId().equals(orderId)){
                return order;
            }
        }
        return null;
    }

    public void deleteOrderById(UUID orderId){
        ArrayList<Order> orders = getOrders();
        for(Order order : orders){
            if(order.getId().equals(orderId)){
                orders.remove(order);
                break;
            }
        }
        saveAll(orders);
    }


    @Override
    protected Class<Order[]> getArrayType() {
        return Order[].class;
    }

    @Override
    protected String getDataPath() {
        return "data/orders.json";
    }
}
