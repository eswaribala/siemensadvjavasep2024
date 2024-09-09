package com.siemens.utils;

import com.siemens.dao.ProductDAO;
import com.siemens.dao.ProductImpl;

import java.util.Map;
import java.util.stream.Collectors;

public class StreamCollectorsDemo {
    public static void main(String[] args){
        //create hashmap product that contains only Id and name from list of products
        ProductDAO productDAO=new ProductImpl();
        //add 100 products
        for(int i=0;i<100;i++){
            productDAO.addProduct(ComparatorLambdaDemo.createProduct());
        }

        //create map from list
        Map<Long,String> mappedProducts=productDAO.getAllProducts().stream()
                .collect(Collectors.toMap(p->p.getProductId(),p->p.getName()));
        //populate map and show key and value

        mappedProducts.entrySet().stream().forEach(entry->System.out
                .println(entry.getKey()+","+entry.getValue()));


    }
}
