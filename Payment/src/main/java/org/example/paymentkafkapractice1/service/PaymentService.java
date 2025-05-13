package org.example.paymentkafkapractice1.service;

import org.example.core.OrderCreatedEvent;

public interface PaymentService {

    void payment(OrderCreatedEvent orderCreatedEvent);

}
