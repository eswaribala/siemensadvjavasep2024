package com.siemens.utils;

import com.siemens.models.Employee;

import java.util.Random;

public class PatternMatcherDemo {
    public static void main(String[] args){

        Employee employee=new Employee();
        employee.setBasicSalary(new Random().nextInt(10000000));
        employee.setBonus(new Random().nextInt(10000,50000));
        //pattern matching java 16
        if(employee instanceof  Employee employeeInstance){
            System.out.println(employeeInstance.getBasicSalary()
                    +","+employeeInstance.getBonus());
        }



    }
}
