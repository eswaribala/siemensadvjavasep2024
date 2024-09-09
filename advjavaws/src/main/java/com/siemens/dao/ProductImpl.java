package com.siemens.dao;

import com.siemens.models.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductImpl implements ProductDAO {
    private List<Product> productList;

    public ProductImpl(){
        productList=new ArrayList<>();
    }
    @Override
    public Product addProduct(Product product) {
        this.productList.add(product);
        return product;
    }

    @Override
    public List<Product> getAllProducts() {
        return productList;
    }
}
