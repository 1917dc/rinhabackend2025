package com.rinha.luiz.service;

import com.rinha.luiz.model.Payment;
import com.rinha.luiz.model.PaymentSummaryResponse;
import com.rinha.luiz.repository.PaymentRepository;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
public class paymentSummaryService {

    private final PaymentRepository paymentRepository;

    public paymentSummaryService(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    public PaymentSummaryResponse summary(Instant from, Instant to) {
        var payments = paymentRepository.findByCreatedAtBetween(from, to)
                .orElseThrow(() -> new IllegalArgumentException("No payments found between " + from + " and " + to));

        var totalAmount = payments.stream()
                .mapToDouble(payment -> payment.getAmount().doubleValue())
                .sum();


        
    }
}
