package org.example.notifications.handler;

import org.example.core.OrderCreatedEvent;
import org.example.notifications.service.NotificationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@KafkaListener(topics = "sent_orders")
public class OrderShippedEventHandler {

    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
    private NotificationService notificationService;

    @Autowired
    public OrderShippedEventHandler(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @KafkaHandler
    public void handle(OrderCreatedEvent orderCreatedEvent) {
        LOGGER.info("Received event: {} ", orderCreatedEvent.getName());
        notificationService.notificate(orderCreatedEvent);
    }

}
