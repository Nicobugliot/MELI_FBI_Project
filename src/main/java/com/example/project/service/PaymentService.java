package com.example.project.service;

import com.example.project.model.Payment;
import com.example.project.response.UserStatusResponse;

import java.util.List;

public interface PaymentService {

    List<Payment> listPayment();

    List<Payment> findPaymentByUserId(Long id);

    UserStatusResponse getUserStatus(Long id, Integer month);

    void savePayment(Payment payment);
}
