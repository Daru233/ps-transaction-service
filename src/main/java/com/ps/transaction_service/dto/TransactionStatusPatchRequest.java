package com.ps.transaction_service.dto;

import com.ps.transaction_service.model.TransactionStatus;
import jakarta.validation.constraints.NotNull;

public record TransactionStatusPatchRequest(
        @NotNull TransactionStatus status
) {}
