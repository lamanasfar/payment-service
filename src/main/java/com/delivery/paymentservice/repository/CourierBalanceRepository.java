package com.delivery.paymentservice.repository;

import com.delivery.paymentservice.entity.CourierBalanceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CourierBalanceRepository extends JpaRepository<CourierBalanceEntity, Long> {

    Optional<CourierBalanceEntity> findByCourierId(Long courierId);
}