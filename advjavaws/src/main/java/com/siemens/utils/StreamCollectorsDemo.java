package com.siemens.utils;

import com.github.javafaker.Faker;
import com.siemens.dao.ProductDAO;
import com.siemens.dao.ProductImpl;
import com.siemens.models.Location;
import com.siemens.models.Product;
import com.siemens.models.ProductV1;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;

public class StreamCollectorsDemo {
    public static void main(String[] args) {
        //create hashmap product that contains only Id and name from list of products
        ProductDAO productDAO = new ProductImpl();
        //add 100 products
        for (int i = 0; i < 100; i++) {
            productDAO.addProduct(ComparatorLambdaDemo.createProduct());
        }

        //create map from list
        Map<Long, String> mappedProducts = productDAO.getAllProducts().stream()
                .collect(Collectors.toMap(p -> p.getProductId(), p -> p.getName()));
        //populate map and show key and value

        mappedProducts.entrySet().stream().forEach(entry -> System.out
                .println(entry.getKey() + "," + entry.getValue()));

        //Group the products by location

        for (int i = 0; i < 100; i++) {
            productDAO.addProductV1(createProductV1());
        }

        Map<Location, List<ProductV1>> groupedByLocation = productDAO.getAllProductsV1().stream()
                .collect(groupingBy(ProductV1::getLocation));

        groupedByLocation.entrySet().stream().forEach(entry -> System.out
                .println(entry.getKey() + "," + entry.getValue()));





    }

    static ProductV1 createProductV1() {
        Faker faker = new Faker();
        return new ProductV1(faker.random().nextInt(100000),
                faker.food().vegetable().toLowerCase(),
                faker.random().nextInt(10000),
                faker.food().measurement(),
                Double.parseDouble(faker.commerce().price()),
                new Location(faker.country().capital(), faker.country().name()));
    }
}