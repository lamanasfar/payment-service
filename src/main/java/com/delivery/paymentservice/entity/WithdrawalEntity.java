package com.delivery.paymentservice.entity;

import com.delivery.paymentservice.enums.WithdrawalStatus;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "withdrawal_table")
@Builder

public class WithdrawalEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private BigDecimal amount;
    @Enumerated(EnumType.STRING)
    private WithdrawalStatus status;
    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name = "courier_balance_id")
    private CourierBalanceEntity courierBalance;
}
