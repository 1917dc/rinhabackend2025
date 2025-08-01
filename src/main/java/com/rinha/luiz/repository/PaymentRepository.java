package com.rinha.luiz.repository;

import com.rinha.luiz.model.Payment;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface PaymentRepository extends CrudRepository<Payment, UUID> {
    Optional<List<Payment>> findByCreatedAtBetween(Instant from, Instant to);
}
