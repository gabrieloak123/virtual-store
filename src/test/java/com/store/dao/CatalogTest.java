package com.store.dao;

import com.store.model.Product;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class CatalogTest {
    private Catalog catalog = new Catalog(new ArrayList<Product>(Arrays.asList(
            new Product(1, "Mousepad", 15.0),
            new Product(2, "Mouse", 20.0),
            new Product(3, "Keyboard", 30.0)
    )));

    @Test
    void testAddProduct() {
        Catalog catalogUpdated = new Catalog(new ArrayList<Product>(Arrays.asList(
                new Product(1, "Mousepad", 15.0),
                new Product(2, "Mouse", 20.0),
                new Product(3, "Keyboard", 30.0),
                new Product(4, "Monitor", 1000.0)
        )));

        catalog.addProduct(new Product(4, "Monitor", 1000.0));

        assertEquals(catalogUpdated.getProducts(), catalog.getProducts());
    }

    @Test
    void testAddProductException() {
        assertThrows(ArrayStoreException.class, () -> catalog.addProduct(new Product(1, "Mousepad", 15.0)));
    }

    @Test
    void testSearchProductById() {
        Product p = new Product(1, "Mousepad", 15.0);

        assertEquals(p, catalog.searchProductById(1));
    }

    @Test
    void testSearchProductByIdException() {
        assertThrows(ArrayStoreException.class, () -> catalog.searchProductById(-1));
    }

    @Test
    void testAddProductMock() {
        Catalog mockCatalog = mock(Catalog.class);

        Product newProduct = new Product(4, "Monitor", 1000.0);
        doNothing().when(mockCatalog).addProduct(newProduct);

        mockCatalog.addProduct(newProduct);

        verify(mockCatalog, times(1)).addProduct(newProduct);
    }

    @Test
    void testAddProductExceptionMock() {
        Catalog mockCatalog = mock(Catalog.class);

        Product existingProduct = new Product(1, "Mousepad", 15.0);
        doThrow(new ArrayStoreException("Product already exists")).when(mockCatalog).addProduct(existingProduct);

        assertThrows(ArrayStoreException.class, () -> mockCatalog.addProduct(existingProduct));

        verify(mockCatalog, times(1)).addProduct(existingProduct);
    }

    @Test
    void testSearchProductByIdMock() {
        Catalog mockCatalog = mock(Catalog.class);

        Product expectedProduct = new Product(1, "Mousepad", 15.0);
        when(mockCatalog.searchProductById(1)).thenReturn(expectedProduct);

        Product actualProduct = mockCatalog.searchProductById(1);

        assertEquals(expectedProduct, actualProduct);
        verify(mockCatalog, times(1)).searchProductById(1);
    }

    @Test
    void testSearchProductByIdExceptionMock() {
        Catalog mockCatalog = mock(Catalog.class);

        when(mockCatalog.searchProductById(-1)).thenThrow(new ArrayStoreException("Invalid product ID"));

        assertThrows(ArrayStoreException.class, () -> mockCatalog.searchProductById(-1));

        verify(mockCatalog, times(1)).searchProductById(-1);
    }
}

