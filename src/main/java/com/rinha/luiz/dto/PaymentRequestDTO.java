package com.rinha.luiz.dto;

import java.math.BigDecimal;
import java.util.UUID;

public record PaymentRequestDTO(
    UUID correlationId,
    BigDecimal amount
) {
}
