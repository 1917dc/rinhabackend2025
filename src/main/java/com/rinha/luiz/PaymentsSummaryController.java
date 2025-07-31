package com.rinha.luiz;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;

@RestController
@RequestMapping("/payments-summary")
public class PaymentsSummaryController {

    @GetMapping
    public HttpStatus paymentsSummary(@RequestParam Instant from, @RequestParam Instant to) {
        System.out.println("Fetching payments summary from " + from + " to " + to);
        return HttpStatus.OK;
    }
}
