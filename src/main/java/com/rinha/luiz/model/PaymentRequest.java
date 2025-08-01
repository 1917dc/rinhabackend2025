package com.rinha.luiz.model;

import java.math.BigDecimal;
import java.util.UUID;

public record PaymentRequest(
    UUID correlationId,
    BigDecimal amount
) {
}
