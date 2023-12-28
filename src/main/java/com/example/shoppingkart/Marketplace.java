package com.example.shoppingkart;

import com.example.shoppingkart.entities.CartItem;
import com.example.shoppingkart.entities.Order;
import com.example.shoppingkart.entities.Product;
import com.example.shoppingkart.entities.User;

import java.util.*;
import java.util.stream.Collectors;

class Marketplace {
    private Map<String, User> users;
    private Map<String, Product> products;
    private Map<String, List<CartItem>> shoppingCarts;
    private List<Order> orders;

    public Marketplace() {
        users = new HashMap<>();
        products = new HashMap<>();
        shoppingCarts = new HashMap<>();
        orders = new ArrayList<>();
    }

    public boolean validateUserLogin(String userId, String password) {
        User user = users.get(userId);
        if (user != null) {
            return user.getPassword().equals(password);
        }
        return false;
    }

    // User Registration
    public void registerUser(User user) {
        // Validate and store user
        // Assume validation logic
        users.put(user.getUserId(), user);
    }

    public User getUser(String userId) {
        return users.getOrDefault(userId, null);
    }

    public List<CartItem> getCart(String userId) {
        return shoppingCarts.getOrDefault(userId, new ArrayList<>());
    }
    // Product Fetch
    public Product getProduct(String productId) {
        return products.get(productId);
    }

    // Add to Cart
    public void addToCart(String userId, String productId, int quantity) {
        // Validate and add to cart
        // Assume validation logic
        Product product = getProduct(productId);
        if (product != null) {
            CartItem cartItem = new CartItem();
            cartItem.setProduct(product);
            cartItem.setQuantity(quantity);

            List<CartItem> cart = shoppingCarts.computeIfAbsent(userId, k -> new ArrayList<>());
            cart.removeIf(item -> item.getProduct().getProductId().equals(productId));
            cart.add(cartItem);
        }
    }

    public void removeFromCart(String userId, String productId) {
        List<CartItem> cart = shoppingCarts.get(userId);
        if (cart != null) {
            cart.removeIf(cartItem -> cartItem.getProduct().getProductId().equals(productId));
        }
    }

    // Checkout
    public void checkout(String userId) {
        // Validate and complete the order
        List<CartItem> cart = shoppingCarts.get(userId);
        if (cart != null && !cart.isEmpty()) {
            Order order = new Order();
            order.setUserId(userId);
            order.setItems(new ArrayList<>(cart));
            order.setOrderDate(new Date());
            order.setStatus(Order.OrderStatus.PENDING);

            // Update product quantity in stock
            for (CartItem item : cart) {
                Product product = item.getProduct();
                product.setQuantity(product.getQuantity() - item.getQuantity());
            }

            // Clear the shopping cart
            cart.clear();

            // Add the order to the order history
            orders.add(order);
        }
    }

    // Get Order History
    public List<Order> getOrderHistory(String userId) {
        return orders.stream()
                .filter(order -> order.getUserId().equals(userId))
                .collect(Collectors.toList());
    }

    public void addProduct(Product product) {
        products.put(product.getProductId(), product);
    }

    // Other methods (getUser, createUser, etc.)
}