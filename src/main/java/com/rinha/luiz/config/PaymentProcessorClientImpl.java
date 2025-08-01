package com.rinha.luiz.config;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import static java.net.http.HttpRequest.BodyPublishers.ofString;

public class PaymentProcessorClientImpl implements PaymentProcessorClient {
    private final String baseUrl;
    private final HttpClient httpClient;

    public PaymentProcessorClientImpl(String baseUrl, HttpClient httpClient) {
        this.baseUrl = baseUrl;
        this.httpClient = httpClient;
    }

    @Override
    public boolean processPayment(String payment) {
        try {
            HttpRequest httpRequest = HttpRequest.newBuilder()
                    .timeout(Duration.ofMillis(10))
                    .uri(URI.create(baseUrl + "/payments"))
                    .POST(ofString(payment))
                    .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                    .build();

            HttpResponse<String> response = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
            return response.statusCode() == 200;
        } catch (Exception e) {
            return false;
        }
    }
}
