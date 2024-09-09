package com.siemens.utils;

import com.github.javafaker.Faker;
import com.siemens.dao.ProductDAO;
import com.siemens.dao.ProductImpl;
import com.siemens.models.Product;

public class ComparatorLambdaDemo {

    public static void main(String[] args){

        ProductDAO productDAO=new ProductImpl();
        //add 100 products
        for(int i=0;i<100;i++){
            productDAO.addProduct(createProduct());
        }

        for(Product product:productDAO.getAllProducts())
            System.out.println(product);

    }

    static Product createProduct(){
        Faker faker=new Faker();
        return new Product(faker.random().nextInt(10000),
                faker.food().vegetable().toLowerCase(),
                faker.random().nextInt(10000),
                faker.food().measurement(),
                Double.parseDouble(faker.commerce().price()));

    }
}
