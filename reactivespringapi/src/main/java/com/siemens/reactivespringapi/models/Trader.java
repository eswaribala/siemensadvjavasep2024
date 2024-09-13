package com.siemens.reactivespringapi.models;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
 
@Scope(scopeName = "request", proxyMode = ScopedProxyMode.TARGET_CLASS)
@Document("traders")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Trader {

	@Id
    int id;
    String name;
    long salary;
 
    //Getters and setters
 
    @Override
    public String toString() {
        return "Trader [id=" + id + ", name=" + name + ", salary=" + salary + "]";
    }
}
