package com.awsDemo.shop.product;

import com.awsDemo.shop.product.dao.ProductRepository;
import com.awsDemo.shop.product.domain.Product;
import com.awsDemo.shop.product.service.ProductService;
import com.awsDemo.shop.product.service.ProductServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class ProductServiceImplTest {
    private ProductRepository productRepository;
    private ProductService productService;

    @Before
    public void init() {
        productRepository = Mockito.mock(ProductRepository.class);
        productService = new ProductServiceImpl(productRepository);
    }

    @Test
    public void testGetProduct_returnValidProduct() {
        Product product = new Product("10", "Coffee", "450");
        Mockito.when(productRepository.getProductById("10")).thenReturn(product);
        Product obtainedProduct = productService.getProductById("10");
        assertEquals(product, obtainedProduct);
        assertNotEquals(new Product("11", "Tea", "380"), obtainedProduct);
    }
}
