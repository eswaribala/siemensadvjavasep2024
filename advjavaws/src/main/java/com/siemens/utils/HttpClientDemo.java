package com.siemens.utils;



import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.net.Authenticator;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpHeaders;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.Map;

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

            //display only country names
            HttpHeaders httpHeaders= httpResponse.headers();
            httpHeaders.map().entrySet().stream().forEach(entry->System.out.println(entry.getKey()
                    +","+entry.getValue()));
            //print all the country names
            JSONArray array = new JSONArray(httpResponse.body().toString());
            JSONObject object=null;

            for(int i=0; i < array.length(); i++)
            {
                object = array.getJSONObject(i);
                System.out.println(object.get("name"));
            }


        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }


    }


}