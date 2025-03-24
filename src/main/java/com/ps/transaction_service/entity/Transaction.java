package com.ps.transaction_service.entity;

import com.ps.transaction_service.model.Currency;
import com.ps.transaction_service.model.TransactionStatus;
import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "transactions")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Transaction {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(nullable = false)
    private UUID userId;

    @Column(nullable = false)
    private BigDecimal amount;

    @Enumerated(EnumType.STRING)
    private Currency currency;

    @Column(nullable = false)
    private String recipientAccount;

    private String description;

    @Enumerated(EnumType.STRING)
    private TransactionStatus status;

    private Instant createdAt;

}
