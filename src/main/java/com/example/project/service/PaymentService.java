package com.example.project.service;

import com.example.project.model.Payment;

import java.util.List;

public interface PaymentService {

    List<Payment> listPayment();

    List<Payment> findPaymentByUserId(Long id);

    List<Payment> findPaymentByUserMonthAndYear(Long id, Integer month, Integer year);

    void savePayment(Long user_id, java.lang.Double amount, Integer month, Integer year, String currency);
}
