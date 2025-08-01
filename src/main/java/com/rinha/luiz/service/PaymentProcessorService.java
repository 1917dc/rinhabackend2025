package com.rinha.luiz.service;

import com.rinha.luiz.config.PaymentProcessorClient;
import com.rinha.luiz.model.PaymentType;
import com.rinha.luiz.repository.PaymentRepository;
import com.rinha.luiz.model.Payment;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.concurrent.LinkedBlockingQueue;

@Service
public class PaymentProcessorService {

    private final PaymentRepository paymentRepository;
    private final LinkedBlockingQueue<Payment> paymentQueue = new LinkedBlockingQueue<>();
    private final PaymentProcessorClient paymentProcessorDefaultClient;
    private final PaymentProcessorClient paymentProcessorFallbackClient;

    public PaymentProcessorService(PaymentRepository paymentRepository,
                                   @Qualifier("paymentProcessorDefaultClient") PaymentProcessorClient paymentProcessorDefaultClient,
                                   @Qualifier("paymentProcessorFallbackClient") PaymentProcessorClient paymentProcessorFallbackClient) {
        this.paymentRepository = paymentRepository;
        this.paymentProcessorDefaultClient = paymentProcessorDefaultClient;
        this.paymentProcessorFallbackClient = paymentProcessorFallbackClient;

        for (int i = 0; i < 20; i++) {
            Thread.startVirtualThread(this::runWorker);
        }
    }

    private void runWorker() {
        while (true) {
            var request = takePayment();
            processPayment(request);
        }
    }

    public void queuePayment(Payment payment) {
        paymentQueue.offer(payment);
    }



    public Payment takePayment() {
        try {
            return paymentQueue.take();
        } catch (InterruptedException err) {
            throw new RuntimeException(err);
        }
    }

    private void processPayment(Payment payment) {
        // tenta realizar o pagamento com função e afere se foi um sucesso ou não
        boolean sucess = executePayment(payment);

        if (sucess) {
            return;
        }

        queuePayment(payment);
    }

    public boolean executePayment(Payment payment){
        try {
            boolean sucess;
            for(int tries = 0; tries < 15; tries++) {
                sucess = sendRequisition(payment, true);

                if(sucess) {
                    payment.setType(PaymentType.DEFAULT);
                    paymentRepository.save(payment);
                    return true;
                }
            }

            sucess = sendRequisition(payment, false);
            if (sucess) {
                payment.setType(PaymentType.FALLBACK);
                paymentRepository.save(payment);
                return true;
            }
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean sendRequisition(Payment payment, boolean retry) {
        try {
            if(retry) {
                return paymentProcessorDefaultClient.processPayment(payment);
            } else {
                return paymentProcessorFallbackClient.processPayment(payment);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
