package com.example.repository;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import com.example.model.Order;

import org.springframework.stereotype.Repository;

import com.example.model.Order;
import com.example.model.Product;
import com.example.model.User;

@Repository
@SuppressWarnings("rawtypes")
public class UserRepository extends MainRepository<User>{

    public UserRepository() {
}

    @Override
    protected String getDataPath() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getDataPath'");
    }

    @Override
    protected Class<User[]> getArrayType() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getArrayType'");
    }

    public ArrayList<User> getUsers(){
        return findAll();
    }
    
    public User getUserById(UUID userId){
        ArrayList<User> users = getUsers();
        for(User user : users){
            if(user.getId().equals(userId)){
                return user;
            }
        }
        return null;
    }
    
    public User addUser(User user){
        save(user);
        return user;
    }
    
    public List<Order> getOrdersByUserId(UUID userId){
        User user = getUserById(userId);
        return (user != null) ? user.getOrders() : new ArrayList<>();
    }
    
    public void addOrderToUser(UUID userId, Order order){
        ArrayList<User> users = getUsers();
        for (User user : users) {
            if (user.getId().equals(userId)) {
                user.getOrders().add(order);
                overrideData(users);
                return;
            }
        }
    }
    
    public void removeOrderFromUser(UUID userId, UUID orderId){
        ArrayList<User> users = getUsers();
        for (User user : users) {
            if (user.getId().equals(userId)) {
                user.getOrders().removeIf(order -> order.getId().equals(orderId));
                overrideData(users);
                return;
            }
        }
    }
    
    public void deleteUserById(UUID userId){
        ArrayList<User> users = getUsers();
        users.removeIf(user -> user.getId().equals(userId));
        overrideData(users);
    }
}