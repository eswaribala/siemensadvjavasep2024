package com.siemens.reactivespringapi;

import org.springframework.boot.SpringApplication;

import org.springframework.boot.autoconfigure.SpringBootApplication;


//@SpringBootApplication
@SpringBootApplication
/*@OpenAPIDefinition(info = @Info(
        title = "Spring WebFlux CRUD Example",
        version = "1.0",
        description = "Spring WebFlux CRUD Example Sample documents"
))*/
public class ReactiveSpringapiApplication {

    public static void main(String[] args) {
        SpringApplication.run(ReactiveSpringapiApplication.class, args);
    }

}
