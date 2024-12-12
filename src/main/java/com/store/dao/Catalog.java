package com.store.dao;

import com.store.model.Product;

import java.util.ArrayList;
import java.util.logging.Logger;

public class Catalog {
    private ArrayList<Product> products;
    private static final Logger logger = Logger.getLogger(Order.class.getName()) ;

    public Catalog() {
        this.products = new ArrayList<Product>();
    }

    public Catalog(ArrayList<Product> products) {
        this.products = products;
    }

    public ArrayList<Product> getProducts() {
        return products;
    }

    public void addProduct(Product p) throws ArrayStoreException{
        for (Product product : products) {
            if(p.getId() == product.getId()) {
                logger.info("Ops, you tried to add an existing product");
                throw new ArrayStoreException("It already exists");
            }
        }
        logger.info(p.getName() + " added to the catalog");
        products.add(p);
    }

    public void listProducts() {
        System.out.println("------------------------------------------------------------------");
        System.out.println("                            Catalog                               ");
        System.out.println("------------------------------------------------------------------");
        String format = "id: %-10s | name: %-15s | price: %.2f%n";

        for (Product p : products) {
            System.out.printf(format, p.getId(), p.getName(), p.getPrice());
            System.out.println("------------------------------------------------------------------");
        }
    }

    public Product searchProductById(int id) throws ArrayStoreException {
        for (Product p : products) {
            if(p.getId() == id) {
                logger.info("The product with this id is: " + p.getName());
                return p;
            }
        }
        logger.info("Ops, this product does not exist");
        throw new ArrayStoreException("Product not found");
    }
}