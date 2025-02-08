package com.fvrvz.ecommerce.kafka;

import com.fvrvz.ecommerce.enums.NotificationType;
import com.fvrvz.ecommerce.models.Notification;
import com.fvrvz.ecommerce.records.OrderConfirmation;
import com.fvrvz.ecommerce.records.PaymentConfirmation;
import com.fvrvz.ecommerce.repositories.NotificationRepository;
import com.fvrvz.ecommerce.services.EmailService;
import jakarta.mail.MessagingException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@Slf4j
public class NotificationConsumer {
    @Autowired
    private NotificationRepository _notificationRepository;

    @Autowired
    private EmailService _emailService;

    @KafkaListener(topics = "payment-topic")
    public void consumePaymentSuccessNotification(PaymentConfirmation paymentConfirmation) throws MessagingException {
        log.info("Consuming the message from payment-topic: {}", paymentConfirmation);
        this._notificationRepository.save(
                Notification.builder()
                        .type(NotificationType.PAYMENT_CONFIRMATION)
                        .notificationDate(LocalDateTime.now())
                        .paymentConfirmation(paymentConfirmation)
                        .build()
        );

        String customerName = paymentConfirmation.customerFirstName()
                .concat(" ")
                .concat(paymentConfirmation.customerLastName());

        this._emailService.sendPaymentSuccessEmail(
                paymentConfirmation.customerEmail(),
                customerName,
                paymentConfirmation.amount(),
                paymentConfirmation.orderReference()
        );
    }

    @KafkaListener(topics = "order-topic")
    public void consumeOrderNotification(OrderConfirmation orderConfirmation) throws MessagingException {
        log.info("Consuming the message from order-topic: {}", orderConfirmation);
        this._notificationRepository.save(
                Notification.builder()
                        .type(NotificationType.ORDER_CONFIRMATION)
                        .notificationDate(LocalDateTime.now())
                        .orderConfirmation(orderConfirmation)
                        .build()
        );

        String customerName = orderConfirmation.customer().firstName()
                .concat(" ")
                .concat(orderConfirmation.customer().lastName());

        this._emailService.sendOrderConfirmationEmail(
                orderConfirmation.customer().email(),
                customerName,
                orderConfirmation.totalAmount(),
                orderConfirmation.orderReference(),
                orderConfirmation.products()
        );
    }
}
