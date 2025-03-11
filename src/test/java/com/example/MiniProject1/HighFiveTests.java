// package com.example.MiniProject1;

// import static org.junit.jupiter.api.Assertions.assertEquals;
// import static org.junit.jupiter.api.Assertions.assertFalse;
// import static org.junit.jupiter.api.Assertions.assertNotNull;
// import static org.junit.jupiter.api.Assertions.assertNull;
// import static org.junit.jupiter.api.Assertions.assertTrue;
// import static org.mockito.ArgumentMatchers.any;
// import static org.mockito.ArgumentMatchers.eq;
// import static org.mockito.Mockito.doNothing;
// import static org.mockito.Mockito.times;
// import static org.mockito.Mockito.verify;
// import static org.mockito.Mockito.when;

// import java.io.File;
// import java.io.IOException;
// import java.util.ArrayList;
// import java.util.Arrays;
// import java.util.HashMap;
// import java.util.List;
// import java.util.Map;
// import java.util.UUID;

// import org.springframework.http.MediaType;
// import org.junit.jupiter.api.BeforeEach;
// import org.junit.jupiter.api.Test;
// import org.mockito.MockitoAnnotations;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.beans.factory.annotation.Value;
// import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
// import org.springframework.context.annotation.ComponentScan;
// import org.springframework.test.web.servlet.MockMvc;
// import org.springframework.test.web.servlet.MvcResult;
// import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
// import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

// import com.example.model.Cart;
// import com.example.model.Order;
// import com.example.model.Product;
// import com.example.model.User;
// import com.example.repository.CartRepository;
// import com.example.repository.OrderRepository;
// import com.example.repository.ProductRepository;
// import com.example.repository.UserRepository;
// import com.example.service.CartService;
// import com.example.service.OrderService;
// import com.example.service.ProductService;
// import com.example.service.UserService;
// import com.fasterxml.jackson.core.JsonProcessingException;
// import com.fasterxml.jackson.core.type.TypeReference;
// import com.fasterxml.jackson.databind.ObjectMapper;

// @ComponentScan(basePackages = "com.example.*")
// @WebMvcTest
// public class HighFiveTests {

//     @Value("${spring.application.userDataPath}")
//     private String userDataPath;

//     @Value("${spring.application.productDataPath}")
//     private String productDataPath;

//     @Value("${spring.application.orderDataPath}")
//     private String orderDataPath;

//     @Value("${spring.application.cartDataPath}")
//     private String cartDataPath;

//     @Autowired
//     private MockMvc mockMvc;

//     @Autowired
//     private ObjectMapper objectMapper;

//     @Autowired
//     private UserService userService;

//     @Autowired
//     private CartService cartService;

//     @Autowired
//     private ProductService productService;

//     @Autowired
//     private OrderService orderService;
//     @Autowired
//     private UserRepository userRepository;

//     @Autowired
//     private CartRepository cartRepository;

//     @Autowired
//     private ProductRepository productRepository;

//     @Autowired
//     private OrderRepository orderRepository;

//     public void overRideAll() {
//         try {
//             objectMapper.writeValue(new File(userDataPath), new ArrayList<User>());
//             objectMapper.writeValue(new File(productDataPath), new ArrayList<Product>());
//             objectMapper.writeValue(new File(orderDataPath), new ArrayList<Order>());
//             objectMapper.writeValue(new File(cartDataPath), new ArrayList<Cart>());
//         } catch (IOException e) {
//             throw new RuntimeException("Failed to write to JSON file", e);
//         }
//     }

//     public Object find(String typeString, Object toFind) {
//         switch (typeString) {
//             case "User":
//                 ArrayList<User> users = getUsers();

//                 for (User user : users) {
//                     if (user.getId().equals(((User) toFind).getId())) {
//                         return user;
//                     }
//                 }
//                 break;
//             case "Product":
//                 ArrayList<Product> products = getProducts();
//                 for (Product product : products) {
//                     if (product.getId().equals(((Product) toFind).getId())) {
//                         return product;
//                     }
//                 }
//                 break;
//             case "Order":
//                 ArrayList<Order> orders = getOrders();
//                 for (Order order : orders) {
//                     if (order.getId().equals(((Order) toFind).getId())) {
//                         return order;
//                     }
//                 }
//                 break;
//             case "Cart":
//                 ArrayList<Cart> carts = getCarts();
//                 for (Cart cart : carts) {
//                     if (cart.getId().equals(((Cart) toFind).getId())) {
//                         return cart;
//                     }
//                 }
//                 break;
//         }
//         return null;
//     }

//     public Product addProduct(Product product) {
//         try {
//             File file = new File(productDataPath);
//             ArrayList<Product> products;
//             if (!file.exists()) {
//                 products = new ArrayList<>();
//             } else {
//                 products = new ArrayList<>(Arrays.asList(objectMapper.readValue(file, Product[].class)));
//             }
//             products.add(product);
//             objectMapper.writeValue(file, products);
//             return product;
//         } catch (IOException e) {
//             throw new RuntimeException("Failed to write to JSON file", e);
//         }
//     }

//     public ArrayList<Product> getProducts() {
//         try {
//             File file = new File(productDataPath);
//             if (!file.exists()) {
//                 return new ArrayList<>();
//             }
//             return new ArrayList<Product>(Arrays.asList(objectMapper.readValue(file, Product[].class)));
//         } catch (IOException e) {
//             throw new RuntimeException("Failed to read from JSON file", e);
//         }
//     }

//     public User addUser(User user) {
//         try {
//             File file = new File(userDataPath);
//             ArrayList<User> users;
//             if (!file.exists()) {
//                 users = new ArrayList<>();
//             } else {
//                 users = new ArrayList<>(Arrays.asList(objectMapper.readValue(file, User[].class)));
//             }
//             users.add(user);
//             objectMapper.writeValue(file, users);
//             return user;
//         } catch (IOException e) {
//             throw new RuntimeException("Failed to write to JSON file", e);
//         }
//     }

//     public ArrayList<User> getUsers() {
//         try {
//             File file = new File(userDataPath);
//             if (!file.exists()) {
//                 return new ArrayList<>();
//             }
//             return new ArrayList<User>(Arrays.asList(objectMapper.readValue(file, User[].class)));
//         } catch (IOException e) {
//             throw new RuntimeException("Failed to read from JSON file", e);
//         }
//     }

//     public Cart addCart(Cart cart) {
//         try {
//             File file = new File(cartDataPath);
//             ArrayList<Cart> carts;
//             if (!file.exists()) {
//                 carts = new ArrayList<>();
//             } else {
//                 carts = new ArrayList<>(Arrays.asList(objectMapper.readValue(file, Cart[].class)));
//             }
//             carts.add(cart);
//             objectMapper.writeValue(file, carts);
//             return cart;
//         } catch (IOException e) {
//             throw new RuntimeException("Failed to write to JSON file", e);
//         }
//     }

//     public ArrayList<Cart> getCarts() {
//         try {
//             File file = new File(cartDataPath);
//             if (!file.exists()) {
//                 return new ArrayList<>();
//             }
//             return new ArrayList<Cart>(Arrays.asList(objectMapper.readValue(file, Cart[].class)));
//         } catch (IOException e) {
//             throw new RuntimeException("Failed to read from JSON file", e);
//         }
//     }

//     public Order addOrder(Order order) {
//         try {
//             File file = new File(orderDataPath);
//             ArrayList<Order> orders;
//             if (!file.exists()) {
//                 orders = new ArrayList<>();
//             } else {
//                 orders = new ArrayList<>(Arrays.asList(objectMapper.readValue(file, Order[].class)));
//             }
//             orders.add(order);
//             objectMapper.writeValue(file, orders);
//             return order;
//         } catch (IOException e) {
//             throw new RuntimeException("Failed to write to JSON file", e);
//         }
//     }

//     public ArrayList<Order> getOrders() {
//         try {
//             File file = new File(orderDataPath);
//             if (!file.exists()) {
//                 return new ArrayList<>();
//             }
//             return new ArrayList<Order>(Arrays.asList(objectMapper.readValue(file, Order[].class)));
//         } catch (IOException e) {
//             throw new RuntimeException("Failed to read from JSON file", e);
//         }
//     }

//     private UUID userId;
//     private User testUser;

//     private UUID productId;
//     private User user;
//     private Product product;
//     private Cart cart;
//     private Order order;

//     @BeforeEach
//     void setUp() {
//         MockitoAnnotations.openMocks(this);
//         userId = UUID.randomUUID();
//         testUser = new User();
//         testUser.setId(userId);
//         testUser.setName("Shouny User");
//         overRideAll();

//         productId = UUID.randomUUID();
//         user = new User(userId, "Shouny User", new ArrayList<>());
//         product = new Product(productId, "Shouny Product", 10.0);
//         cart = new Cart(UUID.randomUUID(), userId, new ArrayList<>());
//         order = new Order(UUID.randomUUID(), userId, 27.0, new ArrayList<>());
//     }

//     // ------------------------ User Tests -------------------------

//     // 1.addUser

//     @Test
//     void testAddUser_Success() throws Exception {
//         // Arrange
//         User newUser = new User(UUID.randomUUID(), "New User", new ArrayList<>());

//         // Act & Assert
//         mockMvc.perform(MockMvcRequestBuilders.post("/user/")
//                 .contentType(MediaType.APPLICATION_JSON)
//                 .content(objectMapper.writeValueAsString(newUser)))
//                 .andExpect(MockMvcResultMatchers.status().isOk());
//     }

//     @Test
//     void testAddUser_MissingName() throws Exception {
//         // Arrange
//         User invalidUser = new User(UUID.randomUUID(), null, new ArrayList<>());

//         // Act & Assert
//         mockMvc.perform(MockMvcRequestBuilders.post("/user/")
//                 .contentType(MediaType.APPLICATION_JSON)
//                 .content(objectMapper.writeValueAsString(invalidUser)))
//                 .andExpect(MockMvcResultMatchers.status().isBadRequest());
//     }

//     @Test
//     void testAddUser_EmptyOrdersList() throws Exception {
//         // Arrange
//         User userWithEmptyOrders = new User(UUID.randomUUID(), "Test User", new ArrayList<>());

//         // Act & Assert
//         mockMvc.perform(MockMvcRequestBuilders.post("/user/")
//                 .contentType(MediaType.APPLICATION_JSON)
//                 .content(objectMapper.writeValueAsString(userWithEmptyOrders)))
//                 .andExpect(MockMvcResultMatchers.status().isOk());
//     }

//     // 2.getUsers

//     @Test
//     void testGetUsers_Success() throws Exception {
//         // Act
//         MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/user/")
//                 .contentType(MediaType.APPLICATION_JSON))
//                 .andExpect(MockMvcResultMatchers.status().isOk())
//                 .andReturn();

//         // Assert
//         assertNotNull(result.getResponse().getContentAsString());
//     }

//     @Test
//     void testGetUsers_NoUsersFound() throws Exception {
//         // Act & Assert
//         mockMvc.perform(MockMvcRequestBuilders.get("/user/")
//                 .contentType(MediaType.APPLICATION_JSON))
//                 .andExpect(MockMvcResultMatchers.status().isOk());
//     }

//     @Test
//     void testGetUsers_ResponseFormat() throws Exception {
//         // Act & Assert
//         mockMvc.perform(MockMvcRequestBuilders.get("/user/")
//                 .contentType(MediaType.APPLICATION_JSON))
//                 .andExpect(MockMvcResultMatchers.status().isOk())
//                 .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON));
//     }

//     // 3.getUserById

//     @Test
//     void testGetUserById_Success() throws Exception {
//         // Arrange: Add a user before calling GET
//         addUser(testUser);

//         // Act & Assert
//         mockMvc.perform(MockMvcRequestBuilders.get("/user/{userId}", testUser.getId()))
//                 .andExpect(MockMvcResultMatchers.status().isOk())
//                 .andExpect(MockMvcResultMatchers.content().json(objectMapper.writeValueAsString(testUser)));
//     }

//     @Test
//     void testGetUserById_UserNotFound() throws Exception {
//         // Act & Assert
//         mockMvc.perform(MockMvcRequestBuilders.get("/user/{userId}", UUID.randomUUID()))
//                 .andExpect(MockMvcResultMatchers.status().isNotFound());
//     }

//     @Test
//     void testGetUserById_InvalidUUIDFormat() throws Exception {

//         // Act & Assert
//         mockMvc.perform(MockMvcRequestBuilders.get("/user/{userId}", "invalid-uuid"))
//                 .andExpect(MockMvcResultMatchers.status().isBadRequest());
//     }

//     // 4.getOrdersByUserId
//     @Test
//     void testGetOrdersByUserId_Success() throws Exception {
//         // Act & Assert
//         mockMvc.perform(MockMvcRequestBuilders.get("/user/{userId}/orders", userId))
//                 .andExpect(MockMvcResultMatchers.status().isOk());
//     }

//     @Test
//     void testGetOrdersByUserId_Success2() throws Exception {
//         // Act & Assert
//         mockMvc.perform(MockMvcRequestBuilders.get("/user/{userId}/orders", userId))
//                 .andExpect(MockMvcResultMatchers.status().isOk());
//     }

//     @Test
//     void testGetOrdersByUserId_ResponseFormat() throws Exception {
//         // Act & Assert
//         mockMvc.perform(MockMvcRequestBuilders.get("/user/{userId}/orders", userId))
//                 .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON));
//     }

//     // 5.addOrderToUser

//     @Test
//     void testAddOrderToUser_Success() throws Exception {
//         // Arrange: Create a user before calling checkout
//         addUser(testUser); // ✅ Ensures the user exists

//         // Act & Assert
//         mockMvc.perform(MockMvcRequestBuilders.post("/user/{userId}/checkout", testUser.getId()))
//                 .andExpect(MockMvcResultMatchers.status().isOk())
//                 .andExpect(MockMvcResultMatchers.content().string("Order added successfully"));
//     }

//     @Test
//     void testAddOrderToUser_Success2() throws Exception {
//         // Arrange: Create a user before calling checkout
//         User veryNewUser = new User(UUID.randomUUID(), "New User", new ArrayList<>());
//         addUser(veryNewUser);

//         // Act & Assert
//         mockMvc.perform(MockMvcRequestBuilders.post("/user/{userId}/checkout", veryNewUser.getId()))
//                 .andExpect(MockMvcResultMatchers.status().isOk())
//                 .andExpect(MockMvcResultMatchers.content().string("Order added successfully"));
//     }

//     @Test
//     void testAddOrderToUser_EmptyCart() throws Exception {
//         // Act & Assert
//         mockMvc.perform(MockMvcRequestBuilders.post("/user/{userId}/checkout", userId))
//                 .andExpect(MockMvcResultMatchers.status().isBadRequest());
//     }

//     // 6.deleteUserById
//     @Test
//     void testDeleteUserById_Success2() throws Exception {
//         // Act & Assert
//         mockMvc.perform(MockMvcRequestBuilders.delete("/user/delete/{userId}", userId))
//                 .andExpect(MockMvcResultMatchers.status().isOk());
//     }

//     @Test
//     void testDeleteUserById_Success() throws Exception {
//         // Arrange: Create a user before deleting
//         addUser(testUser);

//         // Act & Assert
//         mockMvc.perform(MockMvcRequestBuilders.delete("/user/delete/{userId}", testUser.getId()))
//                 .andExpect(MockMvcResultMatchers.status().isOk())
//                 .andExpect(MockMvcResultMatchers.content().string("User deleted successfully"));
//     }

//     @Test
//     void testDeleteUserById_InvalidUUID() throws Exception {
//         // Act & Assert
//         mockMvc.perform(MockMvcRequestBuilders.delete("/user/delete/{userId}", "invalid-uuid"))
//                 .andExpect(MockMvcResultMatchers.status().isBadRequest());
//     }

//     // 7.removeOrderFromUser
//     @Test
//     void testRemoveOrderFromUser_OrderNotFound() throws Exception {
//         // Arrange
//         UUID invalidOrderId = UUID.randomUUID();

//         // Act & Assert
//         mockMvc.perform(MockMvcRequestBuilders.post("/user/{userId}/removeOrder", userId)
//                 .param("orderId", invalidOrderId.toString()))
//                 .andExpect(MockMvcResultMatchers.status().isOk());
//     }

//     @Test
//     void testRemoveOrderFromUser_OrderNotFound2() throws Exception {
//         // Arrange
//         UUID invalidOrderId = UUID.randomUUID();

//         // Act & Assert
//         mockMvc.perform(MockMvcRequestBuilders.post("/user/{userId}/removeOrder", userId)
//                 .param("orderId", invalidOrderId.toString()))
//                 .andExpect(MockMvcResultMatchers.status().isOk());
//     }

//     @Test
//     void testRemoveOrderFromUser_InvalidUUID() throws Exception {
//         // Act & Assert
//         mockMvc.perform(MockMvcRequestBuilders.post("/user/{userId}/removeOrder", userId)
//                 .param("orderId", "invalid-uuid"))
//                 .andExpect(MockMvcResultMatchers.status().isBadRequest());
//     }

//     // 8.emptyCart

//     @Test
//     void testEmptyCart_Success() throws Exception {
//         // Act & Assert
//         mockMvc.perform(MockMvcRequestBuilders.delete("/user/{userId}/emptyCart", userId))
//                 .andExpect(MockMvcResultMatchers.status().isOk())
//                 .andExpect(MockMvcResultMatchers.content().string("Cart emptied successfully"));
//     }

//     @Test
//     void testEmptyCart_Success2() throws Exception {
//         // Arrange: Create a user and add a cart before emptying it
//         addUser(testUser);
//         Product testProduct = new Product(UUID.randomUUID(), "Test Product", 10.0);
//         addProduct(testProduct);
//         Cart testCart = new Cart(UUID.randomUUID(), testUser.getId(), new ArrayList<>(List.of(testProduct)));
//         addCart(testCart); // ✅ Ensures the cart exists

//         // Act & Assert
//         mockMvc.perform(MockMvcRequestBuilders.delete("/user/{userId}/emptyCart", testUser.getId()))
//                 .andExpect(MockMvcResultMatchers.status().isOk())
//                 .andExpect(MockMvcResultMatchers.content().string("Cart emptied successfully"));
//     }

//     @Test
//     void testEmptyCart_EmptyAlready() throws Exception {
//         // Act & Assert
//         mockMvc.perform(MockMvcRequestBuilders.delete("/user/{userId}/emptyCart", userId))
//                 .andExpect(MockMvcResultMatchers.status().isOk())
//                 .andExpect(MockMvcResultMatchers.content().string("Cart emptied successfully"));
//     }

//     // 9.addProductToCart

//     @Test
//     void userTestAddProductToCart_Success() throws Exception {
//         // Arrange
//         UUID productId = UUID.randomUUID();

//         // Act & Assert
//         mockMvc.perform(MockMvcRequestBuilders.put("/user/addProductToCart")
//                 .param("userId", userId.toString())
//                 .param("productId", productId.toString()))
//                 .andExpect(MockMvcResultMatchers.status().isOk())
//                 .andExpect(MockMvcResultMatchers.content().string("Product added to cart"));
//     }

//     @Test
//     void testAddProductToCart_ProductNotFound() throws Exception {
//         // Arrange
//         UUID invalidProductId = UUID.randomUUID();

//         // Act & Assert
//         mockMvc.perform(MockMvcRequestBuilders.put("/user/addProductToCart")
//                 .param("userId", userId.toString())
//                 .param("productId", invalidProductId.toString()))
//                 .andExpect(MockMvcResultMatchers.status().isOk());
//     }

//     @Test
//     void testAddProductToCart_InvalidUUID() throws Exception {
//         // Act & Assert
//         mockMvc.perform(MockMvcRequestBuilders.put("/user/addProductToCart")
//                 .param("userId", userId.toString())
//                 .param("productId", "invalid-uuid"))
//                 .andExpect(MockMvcResultMatchers.status().isBadRequest());
//     }

//     // 10.deleteProductFromCart

//     @Test
//     void testDeleteProductFromCart_Success() throws Exception {
//         // Arrange
//         UUID productId = UUID.randomUUID();

//         // Act & Assert
//         mockMvc.perform(MockMvcRequestBuilders.put("/user/deleteProductFromCart")
//                 .param("userId", userId.toString())
//                 .param("productId", productId.toString()))
//                 .andExpect(MockMvcResultMatchers.status().isOk())
//                 .andExpect(MockMvcResultMatchers.content().string("Product deleted from cart"));
//     }

//     @Test
//     void testDeleteProductFromCart_ProductNotFound() throws Exception {
//         // Arrange
//         UUID invalidProductId = UUID.randomUUID();

//         // Act & Assert
//         mockMvc.perform(MockMvcRequestBuilders.put("/user/deleteProductFromCart")
//                 .param("userId", userId.toString())
//                 .param("productId", invalidProductId.toString()))
//                 .andExpect(MockMvcResultMatchers.status().isOk());
//     }

//     @Test
//     void testDeleteProductFromCart_InvalidUUID() throws Exception {
//         // Act & Assert
//         mockMvc.perform(MockMvcRequestBuilders.put("/user/deleteProductFromCart")
//                 .param("userId", userId.toString())
//                 .param("productId", "invalid-uuid"))
//                 .andExpect(MockMvcResultMatchers.status().isBadRequest());
//     }

//     // ------------------------ Product Tests -------------------------

//     // 1.addProduct

//     @Test
//     void testAddProduct_Success() throws Exception {
//         // Arrange
//         Product newProduct = new Product(UUID.randomUUID(), "New Product", 20.0);

//         // Act & Assert
//         mockMvc.perform(MockMvcRequestBuilders.post("/product/")
//                 .contentType(MediaType.APPLICATION_JSON)
//                 .content(objectMapper.writeValueAsString(newProduct)))
//                 .andExpect(MockMvcResultMatchers.status().isOk());
//     }

//     @Test
//     void testAddProduct_MissingName() throws Exception {
//         // Arrange
//         Product invalidProduct = new Product(UUID.randomUUID(), null, 20.0);

//         // Act & Assert
//         mockMvc.perform(MockMvcRequestBuilders.post("/product/")
//                 .contentType(MediaType.APPLICATION_JSON)
//                 .content(objectMapper.writeValueAsString(invalidProduct)))
//                 .andExpect(MockMvcResultMatchers.status().isOk());
//     }

//     @Test
//     void testAddProduct_NegativePrice() throws Exception {
//         // Arrange
//         Product invalidProduct = new Product(UUID.randomUUID(), "Invalid Product", -10.0);

//         // Act & Assert
//         mockMvc.perform(MockMvcRequestBuilders.post("/product/")
//                 .contentType(MediaType.APPLICATION_JSON)
//                 .content(objectMapper.writeValueAsString(invalidProduct)))
//                 .andExpect(MockMvcResultMatchers.status().isOk());
//     }

//     // 2.getProducts

//     @Test
//     void testGetProducts_Success() throws Exception {
//         // Act
//         MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/product/")
//                 .contentType(MediaType.APPLICATION_JSON))
//                 .andExpect(MockMvcResultMatchers.status().isOk())
//                 .andReturn();

//         // Assert
//         assertNotNull(result.getResponse().getContentAsString());
//     }

//     @Test
//     void testGetProducts_NoProductsFound() throws Exception {
//         // Act & Assert
//         mockMvc.perform(MockMvcRequestBuilders.get("/product/")
//                 .contentType(MediaType.APPLICATION_JSON))
//                 .andExpect(MockMvcResultMatchers.status().isOk());
//     }

//     @Test
//     void testGetProducts_ResponseFormat() throws Exception {
//         // Act & Assert
//         mockMvc.perform(MockMvcRequestBuilders.get("/product/")
//                 .contentType(MediaType.APPLICATION_JSON))
//                 .andExpect(MockMvcResultMatchers.status().isOk())
//                 .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON));
//     }

//     // 3.getProductById

//     @Test
//     void testGetProductById_Success() throws Exception {
//         // Act & Assert
//         mockMvc.perform(MockMvcRequestBuilders.get("/product/{productId}", productId))
//                 .andExpect(MockMvcResultMatchers.status().isOk());
//     }

//     @Test
//     void testGetProductById_ProductNotFound() throws Exception {
//         // Act & Assert
//         mockMvc.perform(MockMvcRequestBuilders.get("/product/{productId}", UUID.randomUUID()))
//                 .andExpect(MockMvcResultMatchers.status().isOk());
//     }

//     @Test
//     void testGetProductById_InvalidUUIDFormat() throws Exception {
//         // Act & Assert
//         mockMvc.perform(MockMvcRequestBuilders.get("/product/{productId}", "invalid-uuid"))
//                 .andExpect(MockMvcResultMatchers.status().isBadRequest());
//     }

//     // 4.updateProduct

//     @Test
//     void testUpdateProduct_Success() throws Exception {
//         // Arrange
//         Map<String, Object> body = new HashMap<>();
//         body.put("newName", "Updated Product");
//         body.put("newPrice", 25.0);

//         // Act & Assert
//         mockMvc.perform(MockMvcRequestBuilders.put("/product/update/{productId}", productId)
//                 .contentType(MediaType.APPLICATION_JSON)
//                 .content(objectMapper.writeValueAsString(body)))
//                 .andExpect(MockMvcResultMatchers.status().isOk());
//     }

//     @Test
//     void testUpdateProduct_ProductNotFound() throws Exception {
//         // Arrange
//         Map<String, Object> body = new HashMap<>();
//         body.put("newName", "Updated Product");
//         body.put("newPrice", 25.0);

//         // Act & Assert
//         mockMvc.perform(MockMvcRequestBuilders.put("/product/update/{productId}", UUID.randomUUID())
//                 .contentType(MediaType.APPLICATION_JSON)
//                 .content(objectMapper.writeValueAsString(body)))
//                 .andExpect(MockMvcResultMatchers.status().isOk());
//     }

//     @Test
//     void testUpdateProduct_NegativePrice() throws Exception {
//         // Arrange
//         Map<String, Object> body = new HashMap<>();
//         body.put("newName", "Updated Product");
//         body.put("newPrice", -10.0);

//         // Act & Assert
//         mockMvc.perform(MockMvcRequestBuilders.put("/product/update/{productId}", productId)
//                 .contentType(MediaType.APPLICATION_JSON)
//                 .content(objectMapper.writeValueAsString(body)))
//                 .andExpect(MockMvcResultMatchers.status().isOk());
//     }

//     // 5.ApplyDiscount

//     @Test
//     void testApplyDiscount_Success() throws Exception {
//         // Arrange
//         ArrayList<UUID> productIds = new ArrayList<>();
//         productIds.add(productId);

//         // Act & Assert
//         mockMvc.perform(MockMvcRequestBuilders.put("/product/applyDiscount")
//                 .param("discount", "10.0")
//                 .content(objectMapper.writeValueAsString(productIds))
//                 .contentType(MediaType.APPLICATION_JSON))
//                 .andExpect(MockMvcResultMatchers.status().isOk())
//                 .andExpect(MockMvcResultMatchers.content().string("Discount applied successfully"));
//     }

//     @Test
//     void testApplyDiscount_ProductNotFound() throws Exception {
//         // Arrange
//         ArrayList<UUID> productIds = new ArrayList<>();
//         productIds.add(UUID.randomUUID());

//         // Act & Assert
//         mockMvc.perform(MockMvcRequestBuilders.put("/product/applyDiscount")
//                 .param("discount", "10.0")
//                 .content(objectMapper.writeValueAsString(productIds))
//                 .contentType(MediaType.APPLICATION_JSON))
//                 .andExpect(MockMvcResultMatchers.status().isOk());
//     }

//     @Test
//     void testApplyDiscount_InvalidDiscount() throws Exception {
//         // Arrange
//         ArrayList<UUID> productIds = new ArrayList<>();
//         productIds.add(productId);

//         // Act & Assert
//         mockMvc.perform(MockMvcRequestBuilders.put("/product/applyDiscount")
//                 .param("discount", "-10.0")
//                 .content(objectMapper.writeValueAsString(productIds))
//                 .contentType(MediaType.APPLICATION_JSON))
//                 .andExpect(MockMvcResultMatchers.status().isOk());
//     }

//     // 6.deleteProductById

//     @Test
//     void testDeleteProductById_Success() throws Exception {
//         // Act & Assert
//         mockMvc.perform(MockMvcRequestBuilders.delete("/product/delete/{productId}", productId))
//                 .andExpect(MockMvcResultMatchers.status().isOk())
//                 .andExpect(MockMvcResultMatchers.content().string("Product deleted successfully"));
//     }

//     @Test
//     void testDeleteProductById_ProductNotFound() throws Exception {
//         // Act & Assert
//         mockMvc.perform(MockMvcRequestBuilders.delete("/product/delete/{productId}", UUID.randomUUID()))
//                 .andExpect(MockMvcResultMatchers.status().isOk());
//     }

//     @Test
//     void testDeleteProductById_InvalidUUID() throws Exception {
//         // Act & Assert
//         mockMvc.perform(MockMvcRequestBuilders.delete("/product/delete/{productId}", "invalid-uuid"))
//                 .andExpect(MockMvcResultMatchers.status().isBadRequest());
//     }

//     // ------------------------ Cart Tests -------------------------

//     // 1.addCart

//     @Test
//     void testAddCart_Success() throws Exception {
//         // Arrange
//         Cart newCart = new Cart(UUID.randomUUID(), userId, new ArrayList<>());

//         // Act & Assert
//         mockMvc.perform(MockMvcRequestBuilders.post("/cart/")
//                 .contentType(MediaType.APPLICATION_JSON)
//                 .content(objectMapper.writeValueAsString(newCart)))
//                 .andExpect(MockMvcResultMatchers.status().isOk());
//     }

//     @Test
//     void testAddCart_UserNotFound() throws Exception {
//         // Arrange
//         Cart cartForInvalidUser = new Cart(UUID.randomUUID(), UUID.randomUUID(), new ArrayList<>());

//         // Act & Assert
//         mockMvc.perform(MockMvcRequestBuilders.post("/cart/")
//                 .contentType(MediaType.APPLICATION_JSON)
//                 .content(objectMapper.writeValueAsString(cartForInvalidUser)))
//                 .andExpect(MockMvcResultMatchers.status().isOk());
//     }

//     @Test
//     void testAddCart_EmptyProductsList() throws Exception {
//         // Arrange
//         Cart cartWithEmptyProducts = new Cart(UUID.randomUUID(), userId, new ArrayList<>());

//         // Act & Assert
//         mockMvc.perform(MockMvcRequestBuilders.post("/cart/")
//                 .contentType(MediaType.APPLICATION_JSON)
//                 .content(objectMapper.writeValueAsString(cartWithEmptyProducts)))
//                 .andExpect(MockMvcResultMatchers.status().isOk());
//     }

//     // 2.getCarts

//     @Test
//     void testGetCarts_Success() throws Exception {
//         // Act
//         MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/cart/")
//                 .contentType(MediaType.APPLICATION_JSON))
//                 .andExpect(MockMvcResultMatchers.status().isOk())
//                 .andReturn();

//         // Assert
//         assertNotNull(result.getResponse().getContentAsString());
//     }

//     @Test
//     void testGetCarts_NoCartsFound() throws Exception {
//         // Act & Assert
//         mockMvc.perform(MockMvcRequestBuilders.get("/cart/")
//                 .contentType(MediaType.APPLICATION_JSON))
//                 .andExpect(MockMvcResultMatchers.status().isOk());
//     }

//     @Test
//     void testGetCarts_ResponseFormat() throws Exception {
//         // Act & Assert
//         mockMvc.perform(MockMvcRequestBuilders.get("/cart/")
//                 .contentType(MediaType.APPLICATION_JSON))
//                 .andExpect(MockMvcResultMatchers.status().isOk())
//                 .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON));
//     }

//     // 3.getCartById

//     @Test
//     void testGetCartById_Success() throws Exception {
//         // Arrange
//         UUID cartId = UUID.randomUUID();

//         // Act & Assert
//         mockMvc.perform(MockMvcRequestBuilders.get("/cart/{cartId}", cartId))
//                 .andExpect(MockMvcResultMatchers.status().isOk());
//     }

//     @Test
//     void testGetCartById_CartNotFound() throws Exception {
//         // Act & Assert
//         mockMvc.perform(MockMvcRequestBuilders.get("/cart/{cartId}", UUID.randomUUID()))
//                 .andExpect(MockMvcResultMatchers.status().isOk());
//     }

//     @Test
//     void testGetCartById_InvalidUUIDFormat() throws Exception {
//         // Act & Assert
//         mockMvc.perform(MockMvcRequestBuilders.get("/cart/{cartId}", "invalid-uuid"))
//                 .andExpect(MockMvcResultMatchers.status().isBadRequest());
//     }

//     // 4.addProductToCart

//     @Test
//     void cartTestAddProductToCart_Success() throws Exception {
//         // Arrange
//         UUID cartId = UUID.randomUUID();
//         Product testProduct = new Product(UUID.randomUUID(), "Test Product", 10.0);

//         // Act & Assert
//         mockMvc.perform(MockMvcRequestBuilders.put("/cart/addProduct/{cartId}", cartId)
//                 .contentType(MediaType.APPLICATION_JSON)
//                 .content(objectMapper.writeValueAsString(testProduct)))
//                 .andExpect(MockMvcResultMatchers.status().isOk())
//                 .andExpect(MockMvcResultMatchers.content().string("Product added successfully"));
//     }

//     @Test
//     void testAddProductToCart_CartNotFound() throws Exception {
//         // Arrange
//         UUID invalidCartId = UUID.randomUUID();
//         Product testProduct = new Product(UUID.randomUUID(), "Test Product", 10.0);

//         // Act & Assert
//         mockMvc.perform(MockMvcRequestBuilders.put("/cart/addProduct/{cartId}", invalidCartId)
//                 .contentType(MediaType.APPLICATION_JSON)
//                 .content(objectMapper.writeValueAsString(testProduct)))
//                 .andExpect(MockMvcResultMatchers.status().isOk());
//     }

//     @Test
//     void testAddProductToCart_InvalidProductData() throws Exception {
//         // Arrange
//         UUID cartId = UUID.randomUUID();
//         Product invalidProduct = new Product(null, "", -5.0); // Invalid product details

//         // Act & Assert
//         mockMvc.perform(MockMvcRequestBuilders.put("/cart/addProduct/{cartId}", cartId)
//                 .contentType(MediaType.APPLICATION_JSON)
//                 .content(objectMapper.writeValueAsString(invalidProduct)))
//                 .andExpect(MockMvcResultMatchers.status().isOk());
//     }

//     // 5.deleteCartById

//     @Test
//     void testDeleteCartById_Success() throws Exception {
//         // Arrange
//         UUID cartId = UUID.randomUUID();

//         // Act & Assert
//         mockMvc.perform(MockMvcRequestBuilders.delete("/cart/delete/{cartId}", cartId))
//                 .andExpect(MockMvcResultMatchers.status().isOk())
//                 .andExpect(MockMvcResultMatchers.content().string("Cart deleted successfully"));
//     }

//     @Test
//     void testDeleteCartById_CartNotFound() throws Exception {
//         // Act & Assert
//         mockMvc.perform(MockMvcRequestBuilders.delete("/cart/delete/{cartId}", UUID.randomUUID()))
//                 .andExpect(MockMvcResultMatchers.status().isOk());
//     }

//     @Test
//     void testDeleteCartById_InvalidUUIDFormat() throws Exception {
//         // Act & Assert
//         mockMvc.perform(MockMvcRequestBuilders.delete("/cart/delete/{cartId}", "invalid-uuid"))
//                 .andExpect(MockMvcResultMatchers.status().isBadRequest());
//     }

//     // ------------------------ Order Tests -------------------------

//     // 1.addOrder

//     @Test
//     void testAddOrder_Success() throws Exception {
//         // Arrange
//         Order newOrder = new Order(UUID.randomUUID(), userId, 100.0, new ArrayList<>());

//         // Act & Assert
//         mockMvc.perform(MockMvcRequestBuilders.post("/order/")
//                 .contentType(MediaType.APPLICATION_JSON)
//                 .content(objectMapper.writeValueAsString(newOrder)))
//                 .andExpect(MockMvcResultMatchers.status().isOk());
//     }

//     @Test
//     void testAddOrder_MissingUserId() throws Exception {
//         // Arrange
//         Order invalidOrder = new Order(UUID.randomUUID(), null, 50.0, new ArrayList<>());

//         // Act & Assert
//         mockMvc.perform(MockMvcRequestBuilders.post("/order/")
//                 .contentType(MediaType.APPLICATION_JSON)
//                 .content(objectMapper.writeValueAsString(invalidOrder)))
//                 .andExpect(MockMvcResultMatchers.status().isOk());
//     }

//     @Test
//     void testAddOrder_EmptyProductList() throws Exception {
//         // Arrange
//         Order orderWithNoProducts = new Order(UUID.randomUUID(), userId, 100.0, new ArrayList<>());

//         // Act & Assert
//         mockMvc.perform(MockMvcRequestBuilders.post("/order/")
//                 .contentType(MediaType.APPLICATION_JSON)
//                 .content(objectMapper.writeValueAsString(orderWithNoProducts)))
//                 .andExpect(MockMvcResultMatchers.status().isOk());
//     }

//     // 2.getOrderById

//     @Test
//     void testGetOrderById_Success() throws Exception {
//         // Arrange
//         UUID orderId = UUID.randomUUID();

//         // Act & Assert
//         mockMvc.perform(MockMvcRequestBuilders.get("/order/{orderId}", orderId))
//                 .andExpect(MockMvcResultMatchers.status().isOk());
//     }

//     @Test
//     void testGetOrderById_OrderNotFound() throws Exception {
//         // Act & Assert
//         mockMvc.perform(MockMvcRequestBuilders.get("/order/{orderId}", UUID.randomUUID()))
//                 .andExpect(MockMvcResultMatchers.status().isOk());
//     }

//     @Test
//     void testGetOrderById_InvalidUUIDFormat() throws Exception {
//         // Act & Assert
//         mockMvc.perform(MockMvcRequestBuilders.get("/order/{orderId}", "invalid-uuid"))
//                 .andExpect(MockMvcResultMatchers.status().isBadRequest());
//     }

//     // 3.getOrders

//     @Test
//     void testGetOrders_Success() throws Exception {
//         // Act
//         MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/order/")
//                 .contentType(MediaType.APPLICATION_JSON))
//                 .andExpect(MockMvcResultMatchers.status().isOk())
//                 .andReturn();

//         // Assert
//         assertNotNull(result.getResponse().getContentAsString());
//     }

//     @Test
//     void testGetOrders_NoOrdersFound() throws Exception {
//         // Act & Assert
//         mockMvc.perform(MockMvcRequestBuilders.get("/order/")
//                 .contentType(MediaType.APPLICATION_JSON))
//                 .andExpect(MockMvcResultMatchers.status().isOk());
//     }

//     @Test
//     void testGetOrders_ResponseFormat() throws Exception {
//         // Act & Assert
//         mockMvc.perform(MockMvcRequestBuilders.get("/order/")
//                 .contentType(MediaType.APPLICATION_JSON))
//                 .andExpect(MockMvcResultMatchers.status().isOk())
//                 .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON));
//     }

//     // 4.deleteOrderById

//     @Test
//     void testDeleteOrderById_Success() throws Exception {
//         // Arrange
//         UUID orderId = UUID.randomUUID();

//         // Act & Assert
//         mockMvc.perform(MockMvcRequestBuilders.delete("/order/delete/{orderId}", orderId))
//                 .andExpect(MockMvcResultMatchers.status().isOk())
//                 .andExpect(MockMvcResultMatchers.content().string("Order deleted successfully"));
//     }

//     @Test
//     void testDeleteOrderById_OrderNotFound() throws Exception {
//         // Act & Assert
//         mockMvc.perform(MockMvcRequestBuilders.delete("/order/delete/{orderId}", UUID.randomUUID()))
//                 .andExpect(MockMvcResultMatchers.status().isOk());
//     }

//     @Test
//     void testDeleteOrderById_InvalidUUIDFormat() throws Exception {
//         // Act & Assert
//         mockMvc.perform(MockMvcRequestBuilders.delete("/order/delete/{orderId}", "invalid-uuid"))
//                 .andExpect(MockMvcResultMatchers.status().isBadRequest());
//     }

// }
