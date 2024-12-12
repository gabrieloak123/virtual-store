package com.store.dao;

import com.store.model.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class OrderTest {
    private Order order = new Order("gabriel", new HashMap<>() {{
        put(new Product(1, "Mousepad", 15.0), 1);
        put(new Product(2, "Mouse", 20.0), 1);
        put(new Product(3, "Keyboard", 30.0), 2);
    }});
    private Order order2 = new Order("gabriel", new HashMap<>() {{
        put(new Product(1, "Mousepad", 40.0), 1);
        put(new Product(2, "Mouse", 20.0), 1);
        put(new Product(3, "Keyboard", 30.0), 2);
    }});
    @Mock
    private Order orderMock;

    @Test
    void testGetClientName() {
       assertEquals("gabriel", order.getClientName());
    }

    @Test
    void testGetProductQuantity() {
        HashMap<Product, Integer> newOrder = new HashMap<>() {{
            put(new Product(1, "Mousepad", 15.0), 1);
            put(new Product(2, "Mouse", 20.0), 1);
            put(new Product(3, "Keyboard", 30.0), 3);
        }};
        order.addOnOrder(new Product(3, "Keyboard", 30.0));

        assertEquals(newOrder, order.getProductQuantity());
    }

    @Test
    void testAddProduct() {
        HashMap<Product, Integer> newOrder = new HashMap<>() {{
            put(new Product(1, "Mousepad", 15.0), 1);
            put(new Product(2, "Mouse", 20.0), 1);
            put(new Product(3, "Keyboard", 30.0), 2);
        }};

        assertEquals(newOrder, order.getProductQuantity());
    }

    @Test
    void testUnder100() {
        assertEquals(95.0, order.calculatePrice());
    }

    @Test
    void testOver100() {
        assertEquals(108.0,order2.calculatePrice());
    }

    @Test
    void testGetClientNameMock() {
        when(orderMock.getClientName()).thenReturn("gabriel");
        assertEquals("gabriel", orderMock.getClientName());
    }

    @BeforeEach
    void setUp() {
        orderMock = mock(Order.class);
    }

    @Test
    void testGetProductQuantityMock() {
        when(orderMock.getProductQuantity()).thenReturn(new HashMap<>() {{
            put(new Product(1, "Mousepad", 40.0), 1);
            put(new Product(2, "Mouse", 20.0), 1);
            put(new Product(3, "Keyboard", 30.0), 2);
        }});
        assertEquals(new HashMap<>() {{
            put(new Product(1, "Mousepad", 40.0), 1);
            put(new Product(2, "Mouse", 20.0), 1);
            put(new Product(3, "Keyboard", 30.0), 2);
        }}, orderMock.getProductQuantity());
    }

    //TODO
    @Test
    void testAddProductMock() {
        Product product1 = new Product(1, "Mousepad", 15.0);
        Product product2 = new Product(2, "Mouse", 20.0);
        Product product3 = new Product(3, "Keyboard", 30.0);

        orderMock.addOnOrder(product1);
        orderMock.addOnOrder(product2);
        orderMock.addOnOrder(product3);
        orderMock.addOnOrder(product3);

        HashMap<Product, Integer> newOrder = new HashMap<>() {{
            put(new Product(1, "Mousepad", 15.0), 1);
            put(new Product(2, "Mouse", 20.0), 1);
            put(new Product(3, "Keyboard", 30.0), 2);
        }};

        verify(orderMock, times(4)).addOnOrder(any(Product.class));
        assertEquals(newOrder, order.getProductQuantity());
    }

    @Test
    void testUnder100Mock() {
        when(orderMock.calculatePrice()).thenReturn(95.0);
        assertEquals(95.0, orderMock.calculatePrice());
    }
}
