package com.ps.transaction_service.config;

import com.ps.transaction_service.service.TransactionService;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

@TestConfiguration
public class TestConfig {
    @Bean
    public TransactionService transactionService() {
        return Mockito.mock(TransactionService.class);
    }
}
