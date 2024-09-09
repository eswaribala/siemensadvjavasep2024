package com.siemens.utils;

import com.siemens.dao.CustomerDao;
import com.siemens.dao.CustomerImpl;
import lombok.NonNull;

import java.util.List;
import java.util.stream.Collectors;

public class VarDemo {
    public static void main(String[] args){
        //type inference
        var code=247687;
        var name="Parameswari";
        System.out.println(code+","+name);
        CustomerDao customerDao=new CustomerImpl();
        CollectAndThenDemo.addCustomers(customerDao);
      List<String> names=
              customerDao.getAllCustomers().stream()
                      .map((@NonNull var x)->x.getName())
                      .collect(Collectors.toList());
      names.stream().forEach(System.out::println);



    }
}
