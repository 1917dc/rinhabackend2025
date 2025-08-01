package com.rinha.luiz.controller;

import com.rinha.luiz.service.PaymentProcessorService;
import com.rinha.luiz.dto.PaymentRequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class PaymentProcessorController {

    @Autowired
    private PaymentProcessorService paymentProcessorService;

    @PostMapping("/payments")
    public void paymentProcessor(@RequestBody PaymentRequestDTO paymentProcessorDTO) {
        paymentProcessorService.queuePayment(paymentProcessorDTO);
    }
}
