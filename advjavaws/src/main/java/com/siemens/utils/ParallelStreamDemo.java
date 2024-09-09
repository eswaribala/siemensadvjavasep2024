package com.siemens.utils;

import java.io.File;

public class ParallelStreamDemo {
    public static void main(String[] args){
        File fileName=new File("Customer Purchasing Behaviors.csv");
        if(fileName.exists())
            System.out.println("Exists");
    }

}
