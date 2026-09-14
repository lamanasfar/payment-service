package com.delivery.paymentservice.controller;

import com.delivery.paymentservice.model.OrderEvent;
import com.delivery.paymentservice.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/payments")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createPaymentRecord(@RequestBody OrderEvent orderEvent) {
        paymentService.createPaymentRecord(orderEvent);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public void completePayment(@RequestBody OrderEvent orderEvent) {
        paymentService.completePayment(orderEvent);
    }
}