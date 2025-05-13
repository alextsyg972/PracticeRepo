package org.example.notifications.service;

import org.example.core.OrderCreatedEvent;

public interface NotificationService {

    void notificate(OrderCreatedEvent orderCreatedEvent);

}
