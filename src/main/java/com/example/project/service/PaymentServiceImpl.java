package com.example.project.service;

import com.example.project.exception.InvalidCurrencyException;
import com.example.project.model.Payment;
import com.example.project.repository.PaymentRepository;
import com.example.project.util.UtilValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentServiceImpl implements PaymentService{

    @Autowired
    private PaymentRepository paymentRepository;

    @Override
    public List<Payment> listPayment(){
        return paymentRepository.findAll();
    }

    @Override
    public List<Payment> findPaymentByUserId(Long id){
        return paymentRepository.findByUserId(id);
    }

    @Override
    public void savePayment(Payment payment){
        if (UtilValidator.validateCurrency(payment.getCurrency())){
            throw new InvalidCurrencyException("Currency is wrong, the values accepted are 'USD' or 'AR'");
        }
        paymentRepository.save(payment);
    }

}
