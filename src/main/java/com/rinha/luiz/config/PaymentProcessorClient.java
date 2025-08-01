package com.rinha.luiz.config;

import com.rinha.luiz.model.Payment;

public interface PaymentProcessorClient {
    boolean processPayment(Payment payment);
}
