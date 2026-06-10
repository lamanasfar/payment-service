package com.delivery.paymentservice.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
@Entity
@Table(name = "courier_balances")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class CourierBalanceEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true)
    private Long courierId;
    @Column(nullable = false)
    private BigDecimal balance; //azala da biler pul cixarsa

    @Column(nullable = false)
    private BigDecimal turnover;//bu deyismeyecek
}

