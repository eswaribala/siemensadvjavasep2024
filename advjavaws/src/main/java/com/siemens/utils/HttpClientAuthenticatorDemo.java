package com.siemens.utils;



import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpHeaders;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.Base64;

public class HttpClientAuthenticatorDemo {
    //step 1
    private static final HttpClient httpClient=HttpClient.newBuilder()
            .version(HttpClient.Version.HTTP_1_1)
            .connectTimeout(Duration.ofMillis(5000))

            .build();

    public static void main(String[] args){
// Create the CredentialsProvider

        HttpRequest httpRequest=HttpRequest.newBuilder()
                .GET()
                .uri(URI.create("https://httpbin.org/basic-auth/user/pass"))
                .header("Authorization", getBasicAuthenticationHeader("user", "pass"))
                .build();

        HttpResponse httpResponse=null;

        try {
            httpResponse=httpClient.send(httpRequest,HttpResponse.BodyHandlers.ofString());

            //display only country names
            HttpHeaders httpHeaders= httpResponse.headers();
            httpHeaders.map().entrySet().stream().forEach(entry->System.out.println(entry.getKey()
                    +","+entry.getValue()));
            System.out.println(httpResponse.body().toString());

        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }


    }

    private static final String getBasicAuthenticationHeader(String username, String password) {
        String valueToEncode = username + ":" + password;
        return "Basic " + Base64.getEncoder().encodeToString(valueToEncode.getBytes());
    }
}
