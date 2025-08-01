package com.rinha.luiz.model;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

public record PaymentProcessor(
        UUID correlationId,
        BigDecimal amount,
        Instant requestedAt
) {
}
