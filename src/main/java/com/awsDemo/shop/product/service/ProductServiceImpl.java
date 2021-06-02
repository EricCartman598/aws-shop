package com.awsDemo.shop.product.service;

import com.awsDemo.shop.product.dao.ProductDao;
import com.awsDemo.shop.product.domain.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {
    private ProductDao productDao;

    @Autowired
    public ProductServiceImpl(ProductDao productDao) {
        this.productDao = productDao;
    }

    @Override
    public Product getProductById(String id) {
        return productDao.getById(Long.parseLong(id));
    }

    @Override
    public String updateProductById(String id) {
        productDao.updateById(Long.parseLong(id));
        return "Ok";
    }
}
