package com.example.project.service;

import com.example.project.exception.InvalidCurrencyException;
import com.example.project.model.Charge;
import com.example.project.model.Payment;
import com.example.project.repository.PaymentRepository;
import com.example.project.response.UserStatusResponse;
import com.example.project.util.UtilValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentServiceImpl implements PaymentService{

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private ChargeService chargeService;

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

    @Override
    public UserStatusResponse getUserStatus(Long id, Integer month){
        List<Charge> charges = chargeService.findChargesByUserIdAndMonth(id, month);
        List<Payment> payments = paymentRepository.findByUserId(id);
        Double totalCharges = chargeService.getTotalCharges(id);
        Double totalPayments = paymentRepository.getTotalPayments(id);
        return new UserStatusResponse(charges, payments, totalPayments, totalCharges);
    }

}
