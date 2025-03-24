package com.ps.transaction_service.controller;

import com.ps.transaction_service.dto.CreateTransactionRequest;
import com.ps.transaction_service.dto.TransactionResponse;
import com.ps.transaction_service.dto.TransactionStatusPatchRequest;
import com.ps.transaction_service.service.TransactionService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.ACCEPTED;
import static org.springframework.http.HttpStatus.OK;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/transactions")
@Validated
public class TransactionController {

    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping
    public ResponseEntity<TransactionResponse> createTransaction(@Valid @RequestBody CreateTransactionRequest request) {
        return ResponseEntity.status(CREATED).body(transactionService.createTransaction(request));
    }

    @GetMapping("/{transactionId}")
    public ResponseEntity<TransactionResponse> getTransactionByID(@PathVariable @NotNull UUID transactionId) {
        return ResponseEntity.status(OK).body(transactionService.getTransactionByID(transactionId));
    }

    @PatchMapping("/{transactionId}/status")
    public ResponseEntity<Void> patchTransactionStatus(
            @PathVariable @NotNull UUID transactionId,
            @Valid @RequestBody TransactionStatusPatchRequest request) {
        transactionService.patchTransactionStatus(transactionId, request);
        return ResponseEntity.status(ACCEPTED).build();
    }

}