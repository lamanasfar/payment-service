package com.delivery.paymentservice.service;

import com.delivery.paymentservice.entity.CourierBalanceEntity;
import com.delivery.paymentservice.entity.PaymentEntity;
import com.delivery.paymentservice.enums.PaymentStatus;
import com.delivery.paymentservice.model.OrderEvent;
import com.delivery.paymentservice.repository.CourierBalanceRepository;
import com.delivery.paymentservice.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Slf4j
@Service
@RequiredArgsConstructor
public class PaymentService {
    private final PaymentRepository  paymentRepository;
    private final CourierBalanceRepository courierBalanceRepository;

    private static final BigDecimal EARNING_RATE = new BigDecimal("0.75");

    public void createPaymentRecord(OrderEvent orderEvent) {
        var courierEarning = orderEvent.getPrice().multiply(EARNING_RATE);

//        PaymentDto dto =
//                PaymentMapper.fromOrderEvent(
//                        orderEvent,
//                        courierEarning);
//
//        paymentRepository.save(
//                PaymentMapper.toEntity(dto)
//        );
        var paymentEntity = PaymentEntity.builder()
                .orderId(orderEvent.getOrderId())
                .courierId(orderEvent.getCourierId())
                .orderAmount(orderEvent.getPrice())
                .courierEarning(courierEarning)
                .status(PaymentStatus.PENDING)
                .createdAt(LocalDateTime.now())
                .build();
        paymentRepository.save(paymentEntity);
        log.info("Payment created: {}", orderEvent.getOrderId());

        createBalanceIfNotExists(orderEvent.getCourierId());
    }

    public void completePayment(OrderEvent event) {
        var record = paymentRepository
                .findByOrderId(event.getOrderId())
                .orElseThrow(() -> new RuntimeException("PaymentRecord not found"));

        record.setStatus(PaymentStatus.COMPLETED);
        record.setCompletedAt(LocalDateTime.now());
        paymentRepository.save(record);

        var balance = courierBalanceRepository
                .findByCourierId(event.getCourierId())
                .orElseThrow(() -> new RuntimeException("Courier balance not found"));

        balance.setBalance(balance.getBalance().add(record.getCourierEarning()));
        balance.setTurnover(balance.getTurnover().add(record.getCourierEarning()));
        courierBalanceRepository.save(balance);

        log.info("Payment completed for orderId: {}", event.getOrderId());
    }

    public static void yest(){

    }

    private void createBalanceIfNotExists(Long courierId) {
        courierBalanceRepository.findByCourierId(courierId).orElseGet(() -> {
            var balance = CourierBalanceEntity.builder()
                    .courierId(courierId)
                    .balance(BigDecimal.ZERO)
                    .turnover(BigDecimal.ZERO)
                    .build();
            return courierBalanceRepository.save(balance);
        });

    }
}
