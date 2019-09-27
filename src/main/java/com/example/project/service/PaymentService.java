package com.example.project.service;

import com.example.project.model.Payment;

import java.util.List;

public interface PaymentService {

    List<Payment> listPayment();

    List<Payment> findPaymentByUserId(Long id);

    void savePayment(Payment payment);
}
