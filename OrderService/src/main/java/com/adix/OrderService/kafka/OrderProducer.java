package com.adix.OrderService.kafka;

import com.adix.OrderService.event.OrderEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import tools.jackson.databind.ObjectMapper;

@Service
@Slf4j
public class OrderProducer {
    private static final String TOPIC="order-events";
    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper;

    public OrderProducer(KafkaTemplate<String, String> kafkaTemplate,ObjectMapper objectMapper) {
        this.kafkaTemplate = kafkaTemplate;
        this.objectMapper= objectMapper;
    }


    public void sendOrderEvent(OrderEvent e) {
        String json = objectMapper.writeValueAsString(e);
        kafkaTemplate.send(TOPIC, String.valueOf(e.getOrderId()), json);
    }
}
