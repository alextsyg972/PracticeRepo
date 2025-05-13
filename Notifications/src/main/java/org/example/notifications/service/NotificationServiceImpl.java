package org.example.notifications.service;

import org.example.core.OrderCreatedEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public class NotificationServiceImpl implements NotificationService {

    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());


    @Override
    public void notificate(OrderCreatedEvent orderCreatedEvent) {
        LOGGER.info("отправили пользователю уведомление");
    }
}
