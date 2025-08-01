package com.rinha.luiz.dto;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

public record PaymentProcessorDTO(
        UUID correlationId,
        BigDecimal amount,
        Instant requestedAt
) {
}
