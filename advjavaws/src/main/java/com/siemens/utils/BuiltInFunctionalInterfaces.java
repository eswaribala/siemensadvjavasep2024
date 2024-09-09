package com.siemens.utils;

import com.siemens.dao.ProductDAO;
import com.siemens.dao.ProductImpl;
import com.siemens.models.Product;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class BuiltInFunctionalInterfaces {

    public static void main(String[] args){
        ProductDAO productDAO=new ProductImpl();
        //add 100 products
        for(int i=0;i<100;i++){
            productDAO.addProduct(ComparatorLambdaDemo.createProduct());
        }

        //filter products > $10 using Function

        Function<List<Product>,List<String>> filteredProductList=(productList)-> {
         return productList.stream().filter(p->p.getCost()>90.00)
                 .map(p->p.getName())
                 .collect(Collectors.toList());
        };

        //invoke the Function
        filteredProductList.apply(productDAO.getAllProducts())
                .stream()
                .forEach(System.out::println);



    }
}
