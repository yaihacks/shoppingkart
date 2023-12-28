package com.example.shoppingkart;
import com.example.shoppingkart.entities.CartItem;
import com.example.shoppingkart.entities.Order;
import com.example.shoppingkart.entities.Product;
import com.example.shoppingkart.entities.User;

import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Example of how to use the Marketplace class

        Marketplace marketplace = new Marketplace();

        Product product1 = new Product("p1","Product 1",19.99,50);

        Product product2 = new Product("p2","Product 2",29.99,30);

        marketplace.addProduct(product1);
        marketplace.addProduct(product2);

        // Add products to the user's shopping cart
        marketplace.addToCart("123", "p1", 3);
        marketplace.addToCart("123", "p2", 2);

        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("1. Login");
            System.out.println("2. Register");
            System.out.println("3. Exit");
            String choice = sc.nextLine();
            if (choice.equals("1")) {
                System.out.println("Enter your email");
                String email = sc.nextLine();
                String userId = String.valueOf(Objects.hash(email));
                System.out.println("Enter your password");
                String pwd = sc.nextLine();
                if(marketplace.validateUserLogin(userId, pwd)){
                    while(true){
                        System.out.println("1. View product ");
                        System.out.println("2. View shopping cart");
                        System.out.println("3. Remove product from cart");
                        System.out.println("4. Checkout");
                        System.out.println("5. View order history");
                        System.out.println("6. Exit");

                        choice = sc.nextLine();

                        if (choice.equals("1")) {
                            System.out.println("Enter product id");
                            String productId = sc.next();
                            Product product = marketplace.getProduct(productId);
                            System.out.println("Product: " + product);
                        } else if (choice.equals("2")) {
                            System.out.println("Your Inventory is");
                            List<CartItem> allProducts = marketplace.getCart(userId);
                            System.out.println(allProducts);
                        } else if (choice.equals("3")) {
                            System.out.println("Enter productId");
                            String productId = sc.next();
                            marketplace.removeFromCart(userId,productId);
                        }
                        else if(choice.equals("4")){
                            marketplace.checkout(userId);
                        }
                        else if(choice.equals("5")){
                            List<Order> orderHistory = marketplace.getOrderHistory(userId);

                            // Display order history
                            for (Order order : orderHistory) {
                                System.out.println("Order ID: " + order.getOrderId());
                                System.out.println("Date: " + order.getOrderDate());
                                System.out.println("Status: " + order.getStatus());
                                System.out.println("Items:");
                                for (CartItem item : order.getItems()) {
                                    System.out.println("  " + item.getProduct().getProductName() + " - Quantity: " + item.getQuantity());
                                }
                                System.out.println();
                            }
                        }
                        else if(choice.equals("6")){
                            break;
                        }
                        else{
                            System.out.println("Wrong Choice");
                        }
                    }
                }
                else{
                    System.out.println("Invalid credentials");
                }
            }
            else if(choice.equals("2")){
                System.out.println("Enter name");
                String name = sc.nextLine();
                System.out.println("Enter email");
                String email = sc.nextLine();
                System.out.println("Enter password");
                String password = sc.nextLine();
                String userId = String.valueOf(Objects.hash(email));
                User user = new User(userId,name,email,password);
                marketplace.registerUser(user);
            }
            else if(choice.equals("3")){
                break;
            }
            else {
                System.out.println("Invalid command");
            }
        }


    }
}