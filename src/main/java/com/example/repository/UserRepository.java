package com.example.repository;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Repository;

import com.example.model.Order;
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
        
    }
    
    public User addUser(User user){
    
    }
    
    public List<Order> getOrdersByUserId(UUID userId){
    
    }
    
    public void addOrderToUser(UUID userId, Order order){
    
    }
    
    public void removeOrderFromUser(UUID userId, UUID orderId){
    
    }
    
    public void deleteUserById(UUID userId){
        
    }
}