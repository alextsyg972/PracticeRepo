package org.example.paymentkafkapractice1.service;

import org.example.core.OrderCreatedEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public class PaymentServiceImpl implements PaymentService {

    private KafkaTemplate<String, OrderCreatedEvent> kafkaTemplate;
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @Autowired
    public PaymentServiceImpl(KafkaTemplate<String, OrderCreatedEvent> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    public void payment(OrderCreatedEvent orderCreatedEvent) {

        orderCreatedEvent.setStatus("PAID");

        CompletableFuture<SendResult<String, OrderCreatedEvent>> future = kafkaTemplate.send("payed_orders",orderCreatedEvent.getId(), orderCreatedEvent);
        future.whenComplete((result, ex) -> {
            if (ex != null) {
                LOGGER.error("Failed to send message: {}", ex.getMessage());
            } else {
                LOGGER.info("Message sent successfully {}", result.getRecordMetadata());
            }
        });
    }
}
