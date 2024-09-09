package com.siemens.utils;

import com.github.javafaker.Faker;
import com.siemens.dao.ProductDAO;
import com.siemens.dao.ProductImpl;
import com.siemens.models.Product;

import java.util.Comparator;

public class ComparatorLambdaDemo {

    public static void main(String[] args){

        ProductDAO productDAO=new ProductImpl();
        //add 100 products
        for(int i=0;i<100;i++){
            productDAO.addProduct(createProduct());
        }


        //without stream how to do comparison
        /*
        Comparator<Product> comparator=(p1,p2)->{
            if(p1.getCost()>p2.getCost())
                return -1;
            else if (p1.getCost()<p2.getCost()) {
                return 1;

            }else
                return 0;
        };
        productDAO.getAllProducts().sort(comparator);
        for(Product product:productDAO.getAllProducts())
            System.out.println(product);
        */

        //with  stream and lambda

        productDAO.getAllProducts().stream().sorted((p1,p2)->{
            if(p1.getCost()>p2.getCost())
                return -1;
            else if (p1.getCost()<p2.getCost())
                return 1;
            else
                return 0;
        }).forEach(System.out::println);


    }

    public static Product createProduct(){
        Faker faker=new Faker();
        return new Product(faker.random().nextInt(10000),
                faker.food().vegetable().toLowerCase(),
                faker.random().nextInt(10000),
                faker.food().measurement(),
                Double.parseDouble(faker.commerce().price()));

    }
}
