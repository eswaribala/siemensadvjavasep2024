package com.siemens.utils;

import com.siemens.dao.ProductDAO;
import com.siemens.dao.ProductImpl;
import com.siemens.models.Category;
import com.siemens.models.Product;

import java.util.List;

public class ImmutableCollectionDemo {
    public static void main(String[] args){
        ProductDAO productDAO = new ProductImpl();
        //add 100 products
        for (int i = 0; i < 100; i++) {
            productDAO.addProduct(ComparatorLambdaDemo.createProduct());
        }
        //immutable list
        List<Product> productList= List.copyOf(productDAO.getAllProducts());
        System.out.println(productList.size());
        //productList.add(ComparatorLambdaDemo.createProduct());

        //record instance
        Category category=new Category(1,"Electronics");
        System.out.println(category.name());


    }
}
