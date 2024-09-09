package com.siemens.dao;

import com.siemens.models.Product;

import java.util.List;

public interface ProductDAO {

    Product addProduct(Product product);
    List<Product> getAllProducts();
}
