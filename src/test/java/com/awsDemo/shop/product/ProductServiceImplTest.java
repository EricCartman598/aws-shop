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
        Product product = new Product(10L, "Coffee", 450L);
        Mockito.when(productRepository.findById(10L)).thenReturn(Optional.of(product));
        Product obtainedProduct = productService.getProductById("10");
        assertEquals(10L, product.getId());
        assertEquals("Coffee", product.getName());
        assertEquals(450L, product.getPrice());
    }
}
