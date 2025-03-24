package com.ps.transaction_service.service;

import com.ps.transaction_service.dto.CreateTransactionRequest;
import com.ps.transaction_service.dto.TransactionResponse;
import com.ps.transaction_service.dto.TransactionStatusPatchRequest;
import com.ps.transaction_service.entity.Transaction;
import com.ps.transaction_service.model.TransactionStatus;
import com.ps.transaction_service.repository.TransactionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.UUID;

@Service
public class TransactionService {

    private static final Logger log = LoggerFactory.getLogger(TransactionService.class);
    private final TransactionRepository transactionRepository;

    public TransactionService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    public TransactionResponse createTransaction(CreateTransactionRequest request) {
        log.info("Creating transaction for user: {}", request.userId());

        Transaction transaction = Transaction.builder()
                .userId(request.userId())
                .amount(request.amount())
                .currency(request.currency())
                .recipientAccount(request.recipientAccount())
                .description(request.description())
                .status(TransactionStatus.PENDING)
                .createdAt(Instant.now())
                .build();

        Transaction saved = transactionRepository.save(transaction);

        return getTransactionResponse(saved);
    }

    public TransactionResponse getTransactionByID(UUID transactionId) {
        Transaction transaction = getByID(transactionId);
        return getTransactionResponse(transaction);
    }

    public void patchTransactionStatus(UUID transactionId, TransactionStatusPatchRequest request) {
       Transaction transaction = getByID(transactionId);
       transaction.setStatus(request.status());
       transactionRepository.save(transaction);
    }

    private Transaction getByID(UUID transactionId) {
        return transactionRepository.findById(transactionId).orElseThrow();
    }

    private TransactionResponse getTransactionResponse(Transaction transaction) {
        return new TransactionResponse(
                transaction.getId(),
                transaction.getUserId(),
                transaction.getAmount(),
                transaction.getCurrency(),
                transaction.getRecipientAccount(),
                transaction.getDescription(),
                transaction.getStatus(),
                transaction.getCreatedAt()
        );
    }

}