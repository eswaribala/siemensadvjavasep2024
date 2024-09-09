package com.siemens.dao;

import com.siemens.models.Product;
import com.siemens.models.ProductV1;

import java.util.List;

public interface ProductDAO {

    Product addProduct(Product product);
    ProductV1 addProductV1(ProductV1 product);
    List<Product> getAllProducts();
    List<ProductV1> getAllProductsV1();
}
