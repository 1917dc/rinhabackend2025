package com.rinha.luiz.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

@RedisHash("payment")
public class Payment {
    @Id private UUID correlationId;
    private BigDecimal amount;
    private Instant requestedAt;
    private Boolean isDefault;

    public Payment() {
    }

    public Payment(UUID correlationId, BigDecimal amount, Instant requestedAt, Boolean isDefault) {
        this.correlationId = correlationId;
        this.amount = amount;
        this.requestedAt = requestedAt;
        this.isDefault = isDefault;
    }

    public UUID getCorrelationId() {
        return correlationId;
    }

    public void setCorrelationId(UUID correlationId) {
        this.correlationId = correlationId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Boolean getDefault() {
        return isDefault;
    }

    public void setDefault(Boolean aDefault) {
        isDefault = aDefault;
    }

    public Instant getRequestedAt() {
        return requestedAt;
    }

    public void setRequestedAt(Instant requestedAt) {
        this.requestedAt = requestedAt;
    }
}
