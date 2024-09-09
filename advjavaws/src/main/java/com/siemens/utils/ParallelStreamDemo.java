package com.siemens.utils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.stream.Stream;

public class ParallelStreamDemo {
    public static void main(String[] args){
        File fileName=new File("Customer Purchasing Behaviors.csv");
        if(fileName.exists()) {
            System.out.println("Exists");
            try {
               Stream<String> lines= Files.lines(fileName.toPath());
               lines.parallel().forEach(System.out::println);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }
    }

}
