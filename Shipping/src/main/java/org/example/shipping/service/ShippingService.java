package org.example.shipping.service;

import org.example.core.OrderCreatedEvent;

public interface ShippingService {

    void shipment(OrderCreatedEvent orderCreatedEvent);

}
