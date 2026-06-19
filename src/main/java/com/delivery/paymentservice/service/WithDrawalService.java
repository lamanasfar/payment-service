package com.delivery.paymentservice.service;

import com.delivery.paymentservice.entity.CourierBalanceEntity;
import com.delivery.paymentservice.entity.WithdrawalEntity;
import com.delivery.paymentservice.enums.WithdrawalStatus;
import com.delivery.paymentservice.repository.CourierBalanceRepository;
import com.delivery.paymentservice.repository.PaymentRepository;
import com.delivery.paymentservice.repository.WithdrawalRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Slf4j
public class WithDrawalService {
    private final WithdrawalRepository withdrawalRepository;
    private final CourierBalanceRepository courierBalanceRepository;

    @Transactional
    public void withdraw(Long courierId, BigDecimal amount ) {
        CourierBalanceEntity courierBalanceEntity = courierBalanceRepository.findById(courierId).orElseThrow(() -> new RuntimeException("Balance not found")); //global
        boolean sufficient = courierBalanceEntity.getBalance().compareTo(amount) >= 0;

        if (sufficient) {
            courierBalanceEntity.setBalance(courierBalanceEntity.getBalance().subtract(amount));
            courierBalanceRepository.save(courierBalanceEntity);
        }

        WithdrawalEntity withdrawalEntity = WithdrawalEntity.builder()
                .amount(amount)
                .createdAt(LocalDateTime.now())
                .courierBalance(courierBalanceEntity)
                .status(sufficient ? WithdrawalStatus.COMPLETED : WithdrawalStatus.REJECTED)
                .build();

         withdrawalRepository.save(withdrawalEntity);
         log.info("withdrawal success");

    }



}
