package com.siemens.dao;

import com.siemens.models.Product;
import com.siemens.models.ProductV1;
import com.siemens.models.ProductV2;

import java.util.List;

public interface ProductDAO {

    Product addProduct(Product product);
    ProductV1 addProductV1(ProductV1 product);
    List<Product> getAllProducts();
    List<ProductV1> getAllProductsV1();
    ProductV2 addProductV2(ProductV2 product);
    List<ProductV2> getAllProductsV2();
}
