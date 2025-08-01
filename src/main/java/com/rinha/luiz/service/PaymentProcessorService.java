package com.rinha.luiz.service;

import com.rinha.luiz.config.PaymentProcessorClient;
import com.rinha.luiz.repository.PaymentRepository;
import com.rinha.luiz.dto.PaymentProcessorDTO;
import com.rinha.luiz.dto.PaymentRequestDTO;
import com.rinha.luiz.model.Payment;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.concurrent.LinkedBlockingQueue;

@Service
public class PaymentProcessorService {

    private final PaymentRepository paymentRepository;
    private final LinkedBlockingQueue<PaymentProcessorDTO> paymentQueue = new LinkedBlockingQueue<>();

    public PaymentProcessorService(PaymentRepository paymentRepository,
                                   @Qualifier("paymentProcessorDefaultClient") PaymentProcessorClient paymentProcessorDefaultClient,
                                   @Qualifier("paymentProcessorFallbackClient") PaymentProcessorClient paymentProcessorFallbackClient) {
        this.paymentRepository = paymentRepository;
    }


    public void queuePayment(PaymentRequestDTO paymentRequestDTO) {
        var paymentProcessor = new PaymentProcessorDTO(
                paymentRequestDTO.correlationId(),
                paymentRequestDTO.amount(),
                Instant.now().truncatedTo(ChronoUnit.SECONDS)
        );
        paymentQueue.offer(paymentProcessor);
    }

    public PaymentProcessorDTO takePayment() {
        try {
            return paymentQueue.take();
        } catch (InterruptedException err) {
            throw new RuntimeException(err);
        }
    }

    public void processPayment(PaymentRequestDTO paymentRequestDTO) {
        // tenta realizar o pagamento com função e afere se foi um sucesso ou não

    }

    public boolean doPayment(Payment payment){
        try {
            boolean sucess;


        }
    }

    public boolean sendRequisition(PaymentRequestDTO paymentRequestDTO) {
        try {

        } catch (Exception e) {
            return false;
        }
    }

    private String escape(String value) {
        return value.replace("\"", "\\\"");
    }

    public String convertToJson(PaymentProcessorDTO request) {
        return """
                {
                  "correlationId": "%s",
                  "amount": %s,
                  "requestedAt": "%s"
                }
                """.formatted(
                escape(request.correlationId().toString()),
                request.amount().toPlainString(),
                request.requestedAt().toString()
        ).replace("\n", "").replace("  ", "");
    }
}
