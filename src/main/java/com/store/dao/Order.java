package com.store.dao;

import com.store.model.Product;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

public class Order {
    private String clientName;
    private Map<Product, Integer> productQuantity;
    private static final Logger logger = Logger.getLogger(Order.class.getName()) ;

    public Order(String clientName) {
        this.clientName = clientName;
        this.productQuantity = new HashMap<Product, Integer>();
        logger.info("Created order for " + clientName);
    }

    public Order(String clientName, Map<Product, Integer> productQuantity) {
        this.clientName = clientName;
        this.productQuantity = productQuantity;
    }

    public String getClientName() {
        return clientName;
    }

    public Map<Product, Integer> getProductQuantity() {
        return productQuantity;
    }

    public void setProductQuantity(Map<Product, Integer> productQuantity) {
        this.productQuantity = productQuantity;
    }

    public void addOnOrder(Product product) {
        //breakpoint here
        if(productQuantity.containsKey(product)) {
            productQuantity.put(product, productQuantity.get(product) + 1);
            logger.info("Now you have " + productQuantity.get(product) + " " + product.getName() + "s added to the order");
        } else {
            productQuantity.put(product, 1);
            logger.info("You added one " + product.getName() + " to your order");
        }
    }

    // Public just for testing
    public double calculatePrice() {
        // breakpoint here
        double total = 0;
        for(Map.Entry<Product, Integer> product : productQuantity.entrySet()) {
            total += product.getValue() * product.getKey().getPrice();
        }

        if(total > 100) {
            logger.info( clientName + ", you got a 10% discount and now you own $" + total * 0.9 + " instead of $" + total);
            return total * 0.9;
        }
        logger.info(clientName + ", you own $" + total);
        return total;
    }

    public void showOrder(){
        String format = "name: %-15s | quantity: %-4s | price: %.2f%n";
        System.out.println("                       " + clientName + "'s order:");
        System.out.println("------------------------------------------------------------------");

        for(Map.Entry<Product, Integer> product : productQuantity.entrySet()) {
            System.out.printf(format, product.getKey().getName(), product.getValue(), product.getKey().getPrice());
        }

        System.out.println("------------------------------------------------------------------");
        System.out.println("Total price: " + calculatePrice());
        System.out.println("------------------------------------------------------------------");
    }
}