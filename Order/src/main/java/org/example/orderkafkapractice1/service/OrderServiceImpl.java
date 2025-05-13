package org.example.orderkafkapractice1.service;

import org.example.core.OrderCreatedEvent;
import org.example.orderkafkapractice1.entity.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@Service
public class OrderServiceImpl implements OrderService {

    private KafkaTemplate<String, OrderCreatedEvent> kafkaTemplate;
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @Autowired
    public OrderServiceImpl(KafkaTemplate<String, OrderCreatedEvent> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }


    @Override
    public Order createOrder(Order order) {

        String productId = UUID.randomUUID().toString();

        OrderCreatedEvent orderCreatedEvent = new OrderCreatedEvent(productId, order.getName(),
                order.getPrice(), order.getAmount(), "CREATED");

        CompletableFuture<SendResult<String, OrderCreatedEvent>> future = kafkaTemplate.send("new_orders", productId, orderCreatedEvent);
        future.whenComplete((result, ex) -> {
            if (ex != null) {
                LOGGER.error("Failed to send message: {}", ex.getMessage());
            } else {
                LOGGER.info("Message sent successfully {}", result.getRecordMetadata());
            }
        });
        LOGGER.info("Return: {}", productId);
        return order;
    }
}
