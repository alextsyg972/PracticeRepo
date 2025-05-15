package org.example.kafkapractice2.handler;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class EventHandler {

    @KafkaListener(topics = "web-logs", concurrency = "6")
    public void handle(ConsumerRecord records) {
        System.out.println(records.value().toString() + " partition: "+ records.partition());
    }
}
