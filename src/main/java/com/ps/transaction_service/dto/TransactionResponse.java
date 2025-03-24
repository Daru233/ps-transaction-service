package com.ps.transaction_service.dto;

import com.ps.transaction_service.model.Currency;
import com.ps.transaction_service.model.TransactionStatus;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

public record TransactionResponse (
    @NotNull UUID transactionId,
    @NotNull UUID userId,
    @DecimalMin("0.01") BigDecimal amount,
    @NotNull Currency currency,
    @NotBlank String recipientAccount,
    String description,
    @NotNull TransactionStatus status,
    @NotNull Instant createdAt
) {}

