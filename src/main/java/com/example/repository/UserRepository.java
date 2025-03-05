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

}