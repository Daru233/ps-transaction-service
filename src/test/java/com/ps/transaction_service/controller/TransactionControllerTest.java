package com.ps.transaction_service.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ps.transaction_service.config.TestConfig;
import com.ps.transaction_service.dto.CreateTransactionRequest;
import com.ps.transaction_service.dto.TransactionResponse;
import com.ps.transaction_service.dto.TransactionStatusPatchRequest;
import com.ps.transaction_service.model.Currency;
import com.ps.transaction_service.model.TransactionStatus;
import com.ps.transaction_service.service.TransactionService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;


@WebMvcTest(TransactionController.class)
@Import(TestConfig.class)
class TransactionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private TransactionService transactionService;


    @Test
    void shouldCreateTransactionAndReturn201() throws Exception {
        // Sample data
        UUID userId = UUID.randomUUID();
        UUID txId = UUID.randomUUID();
        Instant now = Instant.now();

        CreateTransactionRequest request = new CreateTransactionRequest(
                userId,
                new BigDecimal("100.00"),
                Currency.GBP,
                "TEST1ACCOUNT",
                "TEST1ACCOUNT payment"
        );

        TransactionResponse response = new TransactionResponse(
                txId,
                userId,
                request.amount(),
                request.currency(),
                request.recipientAccount(),
                request.description(),
                TransactionStatus.PENDING,
                now
        );

        when(transactionService.createTransaction(request)).thenReturn(response);

        mockMvc.perform(post("/transactions")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.transactionId").value(txId.toString()))
                .andExpect(jsonPath("$.userId").value(userId.toString()))
                .andExpect(jsonPath("$.currency").value("GBP"))
                .andExpect(jsonPath("$.status").value("PENDING"));
    }

    @Test
    void shouldPatchTransactionStatusAndReturn202() throws Exception {
        UUID transactionId = UUID.randomUUID();
        TransactionStatusPatchRequest request = new TransactionStatusPatchRequest(TransactionStatus.APPROVED);
        doNothing().when(transactionService).patchTransactionStatus(transactionId, request);

        mockMvc.perform(patch("/transactions/{transactionId}/status", transactionId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isAccepted());
    }

}