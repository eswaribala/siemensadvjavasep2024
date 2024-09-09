package com.siemens.utils;

import com.github.javafaker.Faker;
import com.siemens.dao.ProductDAO;
import com.siemens.dao.ProductImpl;
import com.siemens.models.Product;

import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Collectors;

public class BuiltInFunctionalInterfaces {

    public static void main(String[] args){
        ProductDAO productDAO=new ProductImpl();
        //add 100 products
        for(int i=0;i<100;i++){
            productDAO.addProduct(ComparatorLambdaDemo.createProduct());
        }

        //filter products > $90 using Function

        Function<List<Product>,List<String>> filteredProductList=(productList)-> {
         return productList.stream().filter(p->p.getCost()>90.00)
                 .map(p->p.getName())
                 .collect(Collectors.toList());
        };

        //invoke the Function
        filteredProductList.apply(productDAO.getAllProducts())
                .stream()
                .forEach(System.out::println);

      //filter products starting with letter b
      System.out.println("Starting with b ..................................");
        filteredProductList=(productList)-> {
            return productList.stream().filter(p->p.getName().startsWith("b"))
                    .map(p->p.getName())
                    .collect(Collectors.toList());
        };

        //invoke the Function
        filteredProductList.apply(productDAO.getAllProducts())
                .stream()
                .forEach(System.out::println);

        //input product and discount and output is final price
        System.out.println("Print Final Price");
        BiFunction<Product,Float,Double> finalPrice=(product,discount)->{
           return product.getCost()- (product.getCost()*discount);

        };
        Faker faker=new Faker();
       System.out.println(finalPrice.apply(new Product(faker.random().nextInt(10000),
                faker.food().vegetable().toLowerCase(),
                faker.random().nextInt(10000),
                faker.food().measurement(),
                Double.parseDouble(faker.commerce().price())),0.05f));
       
       //compare product1 unit with product 2 unit and say are they same or not


    }
}
