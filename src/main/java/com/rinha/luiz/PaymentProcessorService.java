package com.rinha.luiz;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.LinkedBlockingQueue;

@Service
public class PaymentProcessorService {

    private final PaymentRepository paymentRepository;
    private final LinkedBlockingQueue<PaymentProcessorRequest> paymentQueue = new LinkedBlockingQueue<>();

    public PaymentProcessorService(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }


    public void queuePayment(PaymentProcessorRequest paymentProcessorRequest) {
        paymentQueue.offer(paymentProcessorRequest);
    }

    public void processPayment(PaymentProcessorRequest paymentProcessorRequest) {
        var payment = new Payment(
            paymentProcessorRequest.correlationId(),
            paymentProcessorRequest.amount(),
            paymentProcessorRequest.requestedAt()
        );

        doPayment(payment);
    }

    public boolean doPayment(Payment payment){

        return true;
    }
}
