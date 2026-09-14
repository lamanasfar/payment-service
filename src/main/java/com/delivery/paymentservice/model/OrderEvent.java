package com.delivery.paymentservice.model;

import com.delivery.paymentservice.enums.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderEvent {
    private Long orderId;
    private Long courierId;
    private BigDecimal price;
    private OrderStatus status;
}