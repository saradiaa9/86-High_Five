package com.example.service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.example.model.Order;
import com.example.model.User;
import com.example.model.Cart;
import com.example.repository.CartRepository;
import com.example.repository.OrderRepository;
import com.example.repository.ProductRepository;
import com.example.repository.UserRepository;
import com.example.service.MainService;

@Service
@SuppressWarnings("rawtypes")
public class UserService extends MainService<User> {
    private final UserRepository userRepository;
    private final CartRepository cartRepository;
    private final OrderRepository orderRepository;

    public UserService(UserRepository userRepository) {
        super(); // Passing repository to the parent class
        this.userRepository = userRepository;
        this.cartRepository = new CartRepository();
        this.orderRepository = new OrderRepository();
    }

    public User addUser(User user) {
        return userRepository.addUser(user);
    }

    public ArrayList<User> getUsers() {
        return userRepository.getUsers();
    }

    public User getUserById(UUID userId) {
        return userRepository.getUserById(userId);
    }

    public List<Order> getOrdersByUserId(UUID userId) {
        return userRepository.getOrdersByUserId(userId);
    }

    public void addOrderToUser(UUID userId) {
        Cart userCart = cartRepository.getCartByUserId(userId);
        if (userCart == null || userCart.getProducts().isEmpty()) {
            throw new IllegalStateException("Cart is empty or does not exist.");
        }

        double totalPrice = userCart.getProducts().stream().mapToDouble(p -> p.getPrice()).sum();
        Order newOrder = new Order(UUID.randomUUID(), userId, totalPrice, new ArrayList<>(userCart.getProducts()));

        userRepository.addOrderToUser(userId, newOrder);
        orderRepository.addOrder(newOrder);
        emptyCart(userId);
    }

    public void emptyCart(UUID userId) {
        Cart userCart = cartRepository.getCartByUserId(userId);
        if (userCart != null) {
            userCart.getProducts().clear();
            cartRepository.overrideData(cartRepository.getCarts());
        }

    }

    public void removeOrderFromUser(UUID userId, UUID orderId) {
        userRepository.removeOrderFromUser(userId, orderId);
        orderRepository.deleteOrderById(orderId);
    }

    public void deleteUserById(UUID userId) {
        userRepository.deleteUserById(userId);
    }

}
