package com.fvrvz.ecommerce.models;

import com.fvrvz.ecommerce.enums.NotificationType;
import com.fvrvz.ecommerce.records.OrderConfirmation;
import com.fvrvz.ecommerce.records.PaymentConfirmation;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
@Document
public class Notification {
    @Id
    private String id;
    private NotificationType type;
    private LocalDateTime notificationDate;
    private OrderConfirmation orderConfirmation;
    private PaymentConfirmation paymentConfirmation;
}
