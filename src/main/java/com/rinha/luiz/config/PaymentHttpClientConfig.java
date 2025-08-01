package com.rinha.luiz.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.http.HttpClient;

@Configuration
public class PaymentHttpClientConfig {

    @Bean(name = "paymentProcessorDefaultClient")
    public PaymentProcessorClient defaultClient(@Value("${payment.processor.default.url}") String url, HttpClient httpClient, ObjectMapper objectMapper) {
        return new PaymentProcessorClientImpl(url, httpClient, objectMapper);
    }

    @Bean(name = "paymentProcessorFallbackClient")
    public PaymentProcessorClient fallbackClient(@Value("${payment.processor.fallback.url}") String url, HttpClient httpClient, ObjectMapper objectMapper) {
        return new PaymentProcessorClientImpl(url, httpClient, objectMapper);
    }
}
