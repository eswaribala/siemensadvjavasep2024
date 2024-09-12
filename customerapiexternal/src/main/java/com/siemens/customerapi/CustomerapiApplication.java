package com.siemens.customerapi;

import com.siemens.customerapi.configurations.VaultConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
@EnableConfigurationProperties(VaultConfiguration.class)
public class CustomerapiApplication {


    public static void main(String[] args) {

        SpringApplication.run(CustomerapiApplication.class, args);

    }

}
