package com.siemens.utils;

import com.siemens.models.Employee;

public class SwitchExpressionDemo {
    public static void main(String[] args){

        Employee employee=new Employee();
        employee.setBasicSalary(17000);
        employee.setBonus(computeBonus(20,employee));
        System.out.println(employee);


    }

    private static double computeBonus(int profitPerc,Employee employee){
        double computedBonus=0;
        double bonus=switch (profitPerc){
            case 10-> {
                computedBonus=employee.getBasicSalary()*0.10;
                yield computedBonus;
            }
            case 20->{
                computedBonus=employee.getBasicSalary()*0.20;
                yield computedBonus;
            }
            case 50->{
                computedBonus=employee.getBasicSalary()*0.50;
                yield computedBonus;
            }
            case 75->{
                computedBonus=employee.getBasicSalary()*0.75;
                yield computedBonus;
            }
            default -> {
                yield 0;
            }
        };
        return bonus;
    }
}
