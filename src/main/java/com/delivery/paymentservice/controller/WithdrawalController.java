package com.delivery.paymentservice.controller;

import com.delivery.paymentservice.entity.WithdrawalEntity;
import com.delivery.paymentservice.repository.WithdrawalRepository;
import com.delivery.paymentservice.service.WithDrawalService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/payments/withdrawals")
@RequiredArgsConstructor
public class WithdrawalController {
    private final WithDrawalService withdrawalService;
    private final WithdrawalRepository withdrawalRepository;

    @PostMapping("/{courierId}")
    @ResponseStatus(HttpStatus.OK)
    public void withdraw(@PathVariable Long courierId, @RequestBody BigDecimal amount) {
        withdrawalService.withdraw(courierId, amount);
    }

    @GetMapping("/{courierId}")
    public List<WithdrawalEntity> getHistory(@PathVariable Long courierId) {
        return withdrawalRepository.findByCourierBalanceCourierId(courierId);
    }








}
