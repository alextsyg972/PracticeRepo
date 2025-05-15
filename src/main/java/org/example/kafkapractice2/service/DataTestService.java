package org.example.kafkapractice2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Component
public class DataTestService implements CommandLineRunner {

    private final ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
    private final KafkaTemplate<String,Object> kafkaTemplate;

    @Autowired
    public DataTestService(KafkaTemplate<String,Object> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    public void run(String... args) {
        executorService.scheduleAtFixedRate(() -> {
            kafkaTemplate.send("web-logs","data");
        }, 0 ,1000, TimeUnit.MILLISECONDS);
    }

}
