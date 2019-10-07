package com.example.project.service;

import com.example.project.model.Payment;
import com.example.project.request.PaymentRequest;

import java.util.List;

public interface PaymentService {

    List<Payment> listPayment();

    List<Payment> findPaymentByUserId(Long id);

    List<Payment> findPaymentByUserMonthAndYear(Long id, Integer month, Integer year);

    void savePayment(PaymentRequest paymentRequest);
}
