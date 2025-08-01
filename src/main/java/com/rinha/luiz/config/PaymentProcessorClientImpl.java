package com.rinha.luiz.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rinha.luiz.model.Payment;
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
    private final ObjectMapper objectMapper;

    public PaymentProcessorClientImpl(String baseUrl, HttpClient httpClient, ObjectMapper objectMapper) {
        this.baseUrl = baseUrl;
        this.httpClient = httpClient;
        this.objectMapper = objectMapper;
    }

    @Override
    public boolean processPayment(Payment payment) {
        try {
            HttpRequest httpRequest = HttpRequest.newBuilder()
                    .timeout(Duration.ofMillis(10))
                    .uri(URI.create(baseUrl + "/payments"))
                    .POST(ofString(objectMapper.writeValueAsString(payment)))
                    .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                    .build();

            HttpResponse<String> response = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
            return response.statusCode() == 200;
        } catch (Exception e) {
            return false;
        }
    }
}
