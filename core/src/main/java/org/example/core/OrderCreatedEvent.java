package org.example.core;

import java.math.BigDecimal;

public class OrderCreatedEvent {

    private String id;

    private String name;

    private BigDecimal price;

    private Long amount;

    private String status;

    public OrderCreatedEvent() {
    }

    public OrderCreatedEvent(String id, String name, BigDecimal price, Long amount, String status) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.amount = amount;
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }
}
