package com.rinha.luiz.controller;

import com.rinha.luiz.service.PaymentProcessorService;
import com.rinha.luiz.model.PaymentRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.rinha.luiz.model.Payment;
import java.time.Instant;
import java.time.temporal.ChronoUnit;

@RestController
public class PaymentProcessorController {

    @Autowired
    private PaymentProcessorService paymentProcessorService;

    @PostMapping("/payments")
    public void paymentProcessor(@RequestBody PaymentRequest paymentRequest) {
        var payment = new Payment(
                paymentRequest.correlationId(),
                paymentRequest.amount(),
                Instant.now().truncatedTo(ChronoUnit.SECONDS)
        );
        paymentProcessorService.queuePayment(payment);
    }
}
