package com.store.view;

import com.store.dao.Catalog;
import com.store.dao.Order;
import com.store.model.Product;

public class App {
    public static void main(String[] args) {
        Catalog catalog = new Catalog();

        Product product1 = new Product(1, "Mousepad", 15.0);
        Product product2 = new Product(2, "Mouse", 25.0);
        Product product3 = new Product(3, "Keyboard", 45.0);
        Product product4 = new Product(4, "Monitor", 150.0);
        Product product5 = new Product(4, "Headset", 75.0); //Repeted id

        Order myOrder = new Order("Gabriel");
        Order momOrder = new Order("Mom");

        try {
            catalog.addProduct(product1);
            catalog.addProduct(product2);
            catalog.addProduct(product3);
            catalog.addProduct(product4);
            // The line below will try to add a product with repeted id
            // catalog.addProduct(product5);
        } catch (ArrayStoreException e) {
            e.getCause();
        }

        try {
            catalog.listProducts();
            myOrder.addOnOrder(catalog.searchProductById(1));
            myOrder.addOnOrder(catalog.searchProductById(1));
            myOrder.addOnOrder(catalog.searchProductById(2));
            myOrder.addOnOrder(catalog.searchProductById(4));
            momOrder.addOnOrder(catalog.searchProductById(3));
            // The line below will try to add an unexistent product
            // myOrder.addOnOrder(catalog.searchProductById(100));
        } catch (ArrayStoreException e) {
            e.getCause();
        }

        System.out.println();
        myOrder.showOrder();
        System.out.println();
        momOrder.showOrder();
    }
}
