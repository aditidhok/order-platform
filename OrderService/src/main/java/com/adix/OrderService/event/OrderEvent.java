package com.adix.OrderService.event;

import com.adix.OrderService.model.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.convert.DataSizeUnit;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderEvent {
    private Long orderId;
    private String custId;
    private OrderStatus status;
    private BigDecimal totalAmount;
    private LocalDateTime createAt;
    private String eventType;
}
