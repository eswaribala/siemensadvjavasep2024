package com.siemens.utils;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

public class HttpClientDemo {
    //step 1
    private static final HttpClient httpClient=HttpClient.newBuilder()
            .version(HttpClient.Version.HTTP_1_1)
            .connectTimeout(Duration.ofMillis(5000))
            .build();

    public static void main(String[] args){

        HttpRequest httpRequest=HttpRequest.newBuilder()
                .GET()
                .uri(URI.create("https://restcountries.com/v2/all"))
                .build();

        HttpResponse httpResponse=null;

        try {
            httpResponse=httpClient.send(httpRequest,HttpResponse.BodyHandlers.ofString());
            System.out.println(httpResponse.body().toString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }


    }


}
