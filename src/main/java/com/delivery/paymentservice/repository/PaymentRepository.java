package com.delivery.paymentservice.repository;

import com.delivery.paymentservice.entity.PaymentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PaymentRepository extends JpaRepository<PaymentEntity, Long> {

    List<PaymentEntity> findByCourierId(Long courierId);

    Optional<PaymentEntity> findByOrderId(Long orderId);
}