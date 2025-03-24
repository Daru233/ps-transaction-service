package com.ps.transaction_service.dto;

import com.ps.transaction_service.model.Currency;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;
import java.util.UUID;

public record CreateTransactionRequest(
    @NotNull UUID userId,
    @DecimalMin("0.01") BigDecimal amount,
    @NotNull Currency currency,
    @NotBlank String recipientAccount,
    String description
) {}
