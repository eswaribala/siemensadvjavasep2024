package com.siemens.resilience4jdemo.services;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
public class CBCustomerService {
    @Autowired
    private RestClient restClient;
    @Value("${serviceUrl}")
    private String serviceUrl;
    @Value("${alternativeServiceUrl}")
    private String alternativeServiceUrl;

    @CircuitBreaker(name = "gatewayCircuitBreaker", fallbackMethod = "fallback")
    @Retry(name = "gatewayRetry")
    public String getCustomerService(){
      return  restClient.get()
                .uri(serviceUrl)
                .retrieve()
                .body(String.class);

    }

    public String fallback(){
        return  restClient.get()
                .uri(alternativeServiceUrl)
                .retrieve()
                .body(String.class);

    }



}
