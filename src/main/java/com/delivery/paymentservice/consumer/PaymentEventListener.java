package com.delivery.paymentservice.consumer;

import com.delivery.paymentservice.enums.OrderStatus;
import com.delivery.paymentservice.model.OrderEvent;
import com.delivery.paymentservice.service.PaymentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import tools.jackson.databind.ObjectMapper;

@Slf4j
@Component
@RequiredArgsConstructor
public class PaymentEventListener {

    private final PaymentService paymentService;
    private final ObjectMapper objectMapper;


    @RabbitListener(queues = "order.payment.queue")
    public void handleOrderEvent(String message) {
        try {
            OrderEvent event = objectMapper.readValue(message, OrderEvent.class);
            log.info("Event received: {}", event);

            if (OrderStatus.ASSIGNED.equals(event.getStatus())) {
                paymentService.createPaymentRecord(event);
            } else if (OrderStatus.DELIVERED.equals(event.getStatus())) {
                paymentService.completePayment(event);
            }
        } catch (Exception e) {
            log.error("Failed to process event: {}", e.getMessage());
        }
    }
}
