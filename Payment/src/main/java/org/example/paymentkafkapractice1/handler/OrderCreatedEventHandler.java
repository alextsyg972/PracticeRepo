package org.example.paymentkafkapractice1.handler;

import org.example.core.OrderCreatedEvent;
import org.example.paymentkafkapractice1.service.PaymentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@KafkaListener(topics = "new_orders")
public class OrderCreatedEventHandler {

    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
    private PaymentService paymentService;

    @Autowired
    public OrderCreatedEventHandler(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @KafkaHandler
    public void handle(OrderCreatedEvent orderCreatedEvent) {
        LOGGER.info("Received event: {} ", orderCreatedEvent.getName());
        paymentService.payment(orderCreatedEvent);
    }

}
