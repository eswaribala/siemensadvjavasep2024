package com.siemens.utils;

import com.github.javafaker.Faker;
import com.siemens.dao.ProductDAO;
import com.siemens.dao.ProductImpl;
import com.siemens.models.Product;
import com.siemens.models.ProductAggregation;
import com.siemens.models.ProductV2;

import java.math.BigDecimal;
import java.util.stream.Collectors;

public class ProductCustomCollectorDemo {

    public static void main(String[] args){
        ProductDAO productDAO = new ProductImpl();
        //add 100 products
        for (int i = 0; i < 100; i++) {
            productDAO.addProductV2(createProduct());
        }

        ProductAggregation productAggregation=productDAO.getAllProductsV2().stream()
                .collect(new ProductCustomCollector());
        System.out.println("Total Cost="+productAggregation.getTotalCost());
    }

    public static ProductV2 createProduct(){
        Faker faker=new Faker();
        return new ProductV2(faker.random().nextInt(10000),
                faker.food().vegetable().toLowerCase(),
                faker.random().nextInt(10000),
                faker.food().measurement(),
                new BigDecimal(faker.commerce().price()));

    }
}
