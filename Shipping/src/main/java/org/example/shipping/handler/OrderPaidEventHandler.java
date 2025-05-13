package org.example.shipping.handler;

import org.example.core.OrderCreatedEvent;
import org.example.shipping.service.ShippingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@KafkaListener(topics = "payed_orders")
public class OrderPaidEventHandler {

    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
    private ShippingService shippingService;

    @Autowired
    public OrderPaidEventHandler(ShippingService shippingService) {
        this.shippingService = shippingService;
    }

    @KafkaHandler
    public void handle(OrderCreatedEvent orderCreatedEvent) {
        LOGGER.info("Received event: {} ", orderCreatedEvent.getName());
        shippingService.shipment(orderCreatedEvent);
    }

}
