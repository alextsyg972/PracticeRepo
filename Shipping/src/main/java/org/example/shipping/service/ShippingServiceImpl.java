package org.example.shipping.service;

import org.example.core.OrderCreatedEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public class ShippingServiceImpl implements ShippingService {

    private KafkaTemplate<String, Object> kafkaTemplate;
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @Autowired
    public ShippingServiceImpl(KafkaTemplate<String, Object> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    public void shipment(OrderCreatedEvent orderCreatedEvent) {
        orderCreatedEvent.setStatus("SHIPPING");
        CompletableFuture<SendResult<String, Object>> future = kafkaTemplate.send("sent_orders", orderCreatedEvent.getId(), orderCreatedEvent);
        future.whenComplete((result, ex) -> {
            if (ex != null) {
                LOGGER.error("Failed to send message: {}", ex.getMessage());
            } else {
                LOGGER.info("Message sent successfully {}", result.getRecordMetadata());
            }
        });

    }
}
