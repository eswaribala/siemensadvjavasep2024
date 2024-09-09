package com.siemens.utils;

import com.github.javafaker.Faker;
import com.siemens.dao.CartDao;
import com.siemens.dao.ProductDAO;
import com.siemens.dao.ProductImpl;
import com.siemens.dao.TriFunction;
import com.siemens.models.Product;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.function.*;
import java.util.stream.Collectors;
@Slf4j
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
        Product productInstance=new Product(faker.random().nextInt(10000),
                faker.food().vegetable().toLowerCase(),
                faker.random().nextInt(10000),
                faker.food().measurement(),
                Double.parseDouble(faker.commerce().price()));

       System.out.println(finalPrice.apply(productInstance,0.05f));

       //compare product1 unit with product 2 unit and say are they same or not

       //supplier to create the instance
        Supplier<Product> productSupplier=Product::new;
        System.out.println(productSupplier.get().getProductId());

       //Generate OTP to confirm the cart
        //method reference
       Supplier<Integer> generatedOTP= CartDao::generateOTP;
        System.out.println("OTP->"+generatedOTP.get());
       //logging
       Consumer<Product> consumerProduct= (product)->{
           if (product.getCost()>90.00)
                 log.info("Quite costly");
           else
               log.info("Quite affordable");
       };

       consumerProduct.accept(productInstance);


       Predicate<Product> testProduct=(product)->{
           return product.getName().startsWith("a");
       };
      log.info(""+testProduct.test(productInstance) );

      //bipredicate compare two product prices
        BiPredicate<Product,Product> testProductPrices=(p1,p2)->{
            return p1.getCost()==p2.getCost();
        };
       log.info(""+testProductPrices.test(productInstance,productInstance));


        TriFunction<Product,Product,Product,Boolean> compareProductPrices=(p1,p2,p3)->{
             boolean status=false;
            if(p1.getCost()>p2.getCost()){
                if(p1.getCost()>p3.getCost())
                    status= true;
                else
                    status= false;
            }else{
                if(p2.getCost()>p3.getCost())
                    status= true;
                else
                    status= false;
            }
           return status;
        };

        log.info(""+compareProductPrices.apply(productInstance,productInstance,productInstance));

    }
}
