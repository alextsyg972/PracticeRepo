package org.example.notifications.service;

import org.example.core.OrderCreatedEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class NotificationServiceImpl implements NotificationService {

    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());


    @Override
    public void notificate(OrderCreatedEvent orderCreatedEvent) {
        LOGGER.info("отправили пользователю уведомление");
    }
}
