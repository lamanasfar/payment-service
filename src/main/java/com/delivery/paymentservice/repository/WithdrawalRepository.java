package com.delivery.paymentservice.repository;

import com.delivery.paymentservice.entity.WithdrawalEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WithdrawalRepository  extends JpaRepository<WithdrawalEntity,Long> {
    List<WithdrawalEntity> findByCourierBalanceCourierId(Long courierId); //for history

}
