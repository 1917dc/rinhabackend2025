package com.rinha.luiz;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
public class PaymentProcessorController {

    @Autowired
    PaymentRepository paymentRepository;

    @PostMapping("/payments")
    public HttpStatus paymentProcessor(@RequestBody PaymentProcessorRequest paymentProcessorDTO) {
        System.out.println("Payment processor called with: " + paymentProcessorDTO);

        return HttpStatus.OK;
    }
}
