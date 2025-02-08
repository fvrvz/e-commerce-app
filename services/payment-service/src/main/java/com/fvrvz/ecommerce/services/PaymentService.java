package com.fvrvz.ecommerce.services;

import com.fvrvz.ecommerce.kafka.NotificationProducer;
import com.fvrvz.ecommerce.mappers.PaymentMapper;
import com.fvrvz.ecommerce.records.PaymentNotificationRequest;
import com.fvrvz.ecommerce.records.PaymentRequest;
import com.fvrvz.ecommerce.repositories.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {
    @Autowired
    private PaymentRepository _paymentRepository;

    @Autowired
    private PaymentMapper _paymentMapper;

    @Autowired
    private NotificationProducer _notificationProducer;

    public Integer createPayment(PaymentRequest request) {
        var payment = this._paymentRepository.save(this._paymentMapper.toPayment(request));
        this._notificationProducer.sendNotification(new PaymentNotificationRequest(
                request.orderReference(),
                request.amount(),
                request.paymentMethod(),
                request.customer().firstName(),
                request.customer().lastName(),
                request.customer().email()
        ));

        return payment.getId();
    }
}
