package com.example.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.model.Order;
import com.example.model.User;
import com.example.service.CartService;
import com.example.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;
    private final CartService cartService;

    public UserController(UserService userService, CartService cartService) {
        this.cartService = cartService;
        this.userService = userService;
    }

    @PostMapping("/")
    public User addUser(@RequestBody User user){
        return userService.addUser(user);
    }

    @GetMapping("/")
    public ArrayList<User> getUsers(){
        return userService.getUsers();
    }

    @GetMapping("/{userId}")
    public User getUserById(@PathVariable UUID userId){
        return userService.getUserById(userId);
    }

    @GetMapping("/{userId}/orders")
    public List<Order> getOrdersByUserId(@PathVariable UUID userId){
        return userService.getOrdersByUserId(userId);
    }

    @PostMapping("/{userId}/checkout")
    public void addOrderToUser(@PathVariable UUID userId){
        userService.addOrderToUser(userId);
    }

    @PostMapping("/{userId}/removeOrder")
    public String removeOrderFromUser(@PathVariable UUID userId, @RequestParam UUID orderId){
        userService.removeOrderFromUser(userId, orderId);
        return "Order removed successfully";
    }
    
    @DeleteMapping("/{userId}/emptyCart")
    public String emptyCart(@PathVariable UUID userId){
        userService.emptyCart(userId);
        return "Cart emptied successfully";
    }

    @PutMapping("/addProductToCart")
    public String addProductToCart(@RequestParam UUID userId, @RequestParam UUID productId){
        cartService.addProductToCart(userId, productId);
        return "Product added to cart successfully";
    }

    @PutMapping("/deleteProductFromCart")
    public String deleteProductFromCart(@RequestParam UUID userId, @RequestParam UUID productId){
        cartService.deleteProductFromCart(userId, productId);
        return "Product deleted from cart successfully";
    }

    @DeleteMapping("/delete/{userId}")
    public String deleteUserById(@PathVariable UUID userId){
        userService.deleteUserById(userId);
        return "User deleted successfully";
    }
}
