package com.delivery.paymentservice.consumer;

import com.delivery.paymentservice.model.OrderEvent;
import com.delivery.paymentservice.service.PaymentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class PaymentEventListener {

    private final PaymentService paymentService;

    @RabbitListener(queues = "order.payment.queue")
    public void handleOrderEvent(OrderEvent event) {
        log.info("Event received: {}", event);

        if ("ORDER_ASSIGNED".equals(event.getStatus())) {
            paymentService.createPaymentRecord(event);
        } else if ("ORDER_DELIVERED".equals(event.getStatus())) {
            paymentService.completePayment(event);
        }
    }
}
