package com.spartaglobal.samurah.util;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Connection {
    private final HttpClient httpClient;
    private final String url;
    private HttpRequest httpRequest;
    private HttpResponse<String> httpResponse;

    private Connection(String url) throws IOException, InterruptedException {
        this.url = url;
        this.httpClient = HttpClient.newHttpClient();
        connectToAPI(url);
    }

    public static Connection connect(String url) throws IOException, InterruptedException {
        return new Connection(url);
    }

    public static Connection connect(URL url) throws IOException, InterruptedException {
        return new Connection(url.toString());
    }

    private void connectToAPI(String url) throws IOException, InterruptedException {
        this.httpRequest = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .build();
        this.httpResponse = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
        System.out.println(url);
    }

    public int statusCode() {
        return httpResponse.statusCode();
    }

    public String getURL() {
        return url;
    }

    public HttpClient getClient() {
        return httpClient;
    }

    public HttpRequest getRequest() {
        return httpRequest;
    }

    public HttpResponse<String> getResponse() {
        return httpResponse;
    }

}
