package com.example.repository;

import org.springframework.stereotype.Repository;

import com.example.model.Cart;
import com.example.model.Product;

import java.util.ArrayList;
import java.util.UUID;

@Repository
@SuppressWarnings("rawtypes")
public class CartRepository extends MainRepository<Cart> {

    public CartRepository() {}
    
    public Cart addCart(Cart cart) {
        save(cart);
        return cart;
    }
    
    public ArrayList<Cart> getCarts() {
        return findAll();
    }
    
    public Cart getCartById(UUID cartId) {
      
        return findAll().stream()
                .filter(cart -> cart.getId().equals(cartId))
                .findFirst()
                .orElse(null);
    }
    
    public Cart getCartByUserId(UUID userId) {
        return findAll().stream()
                .filter(cart -> cart.getUserId().equals(userId))
                .findFirst()
                .orElse(null);
    }
    
    public void addProductToCart(UUID cartId, Product product) {
        Cart cart = getCartById(cartId);
        if (cart != null) {
            cart.getProducts().add(product);
            save(cart);
        }
    }
    
    public void deleteProductFromCart(UUID cartId, Product product) {
        Cart cart = getCartById(cartId);
        if (cart != null) {
            cart.getProducts().remove(product);
            save(cart);
        }
    }
    
    public void deleteCartById(UUID cartId) {
        ArrayList<Cart> carts = findAll();
        carts.removeIf(cart -> cart.getId().equals(cartId));
        saveAll(carts);
    }

    @Override
    protected String getDataPath() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getDataPath'");
    }

    @Override
    protected Class<Cart[]> getArrayType() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getArrayType'");
    }
}

