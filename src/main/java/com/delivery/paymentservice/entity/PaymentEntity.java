package com.delivery.paymentservice.entity;

import com.delivery.paymentservice.enums.PaymentStatus;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "payment_table")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PaymentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Long orderId;

    @Column
    private Long courierId;

    @Column
    private BigDecimal orderAmount; //orderin qiymeti

    @Column
    private BigDecimal courierEarning; //kuryerin qazanci

    @Enumerated(EnumType.STRING)
    @Column
    private PaymentStatus status;

    @Column
    private LocalDateTime createdAt;

    @Column
    private LocalDateTime completedAt;

    @Column
    private BigDecimal platformFee;
}