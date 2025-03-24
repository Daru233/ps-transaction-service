package com.ps.transaction_service.service;

import com.ps.transaction_service.dto.CreateTransactionRequest;
import com.ps.transaction_service.dto.TransactionResponse;
import com.ps.transaction_service.entity.Transaction;
import com.ps.transaction_service.model.TransactionStatus;
import com.ps.transaction_service.repository.TransactionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.Instant;

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

        return new TransactionResponse(
                saved.getId(),
                saved.getUserId(),
                saved.getAmount(),
                saved.getCurrency(),
                saved.getRecipientAccount(),
                saved.getDescription(),
                saved.getStatus(),
                saved.getCreatedAt()
        );

    }

}