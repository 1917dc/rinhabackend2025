package com.rinha.luiz;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

public record PaymentProcessorRequest(
    UUID correlationId,
    BigDecimal amount,
    Instant requestedAt
) {
}
