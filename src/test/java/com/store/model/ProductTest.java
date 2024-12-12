package com.store.model;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ProductTest {
    private final Product arroz = new Product(1, "arroz", 5.99);

    @Mock
    private Product mock;

    @Test
    void testGetId() {
        assertEquals(1, arroz.getId());
    }

    @Test
    void testGetName() {
        assertEquals("arroz", arroz.getName());
    }

    @Test
    void testGetPrice() {
        assertEquals(5.99, arroz.getPrice());
    }

    @Test
    void testGetIdWithMock() {
        when(mock.getId()).thenReturn(1);
        assertEquals(1, mock.getId());
    }

    @Test
    void testGetNameWithMock() {
        when(mock.getName()).thenReturn("arroz");
        assertEquals("arroz", mock.getName());
    }

    @Test
    void testGetPriceWithMock() {
        when(mock.getPrice()).thenReturn(5.99);
        assertEquals(5.99, mock.getPrice());
    }
}
