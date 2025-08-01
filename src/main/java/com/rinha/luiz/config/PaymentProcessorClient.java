package com.rinha.luiz.config;

import com.rinha.luiz.dto.PaymentRequestDTO;

public interface PaymentProcessorClient {
    boolean processPayment(String payment);
}
