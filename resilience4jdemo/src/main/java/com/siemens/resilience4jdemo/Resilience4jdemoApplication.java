package com.siemens.resilience4jdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestClient;

@SpringBootApplication
public class Resilience4jdemoApplication {

    public static void main(String[] args) {

        SpringApplication.run(Resilience4jdemoApplication.class, args);
    }

    @Bean
    RestClient getInstance(){
        return RestClient.create();
    }

}
