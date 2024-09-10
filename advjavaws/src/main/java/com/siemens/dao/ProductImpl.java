package com.siemens.dao;

import com.siemens.models.Product;
import com.siemens.models.ProductV1;
import com.siemens.models.ProductV2;

import java.util.ArrayList;
import java.util.List;

public class ProductImpl implements ProductDAO {
    private List<Product> productList;
    private List<ProductV1> productListV1;
    private List<ProductV2> productListV2;

    public ProductImpl(){

        productList=new ArrayList<>();
        productListV1=new ArrayList<>();
        productListV2=new ArrayList<>();
    }
    @Override
    public Product addProduct(Product product) {
        this.productList.add(product);
        return product;
    }

    @Override
    public ProductV1 addProductV1(ProductV1 product) {
        this.productListV1.add(product);
        return product;
    }
    @Override
    public ProductV2 addProductV2(ProductV2 product) {
        this.productListV2.add(product);
        return product;
    }
    @Override
    public List<Product> getAllProducts() {
        return productList;
    }

    @Override
    public List<ProductV1> getAllProductsV1() {
        return productListV1;
    }
    @Override
    public List<ProductV2> getAllProductsV2() {
        return productListV2;
    }
}
