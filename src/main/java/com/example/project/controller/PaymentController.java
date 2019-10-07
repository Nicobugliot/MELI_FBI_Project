package com.example.project.controller;


import com.example.project.exception.InvalidAmountException;
import com.example.project.exception.InvalidCurrencyException;
import com.example.project.model.Payment;
import com.example.project.request.PaymentRequest;
import com.example.project.service.PaymentService;
import com.example.project.util.UtilConverter;
import com.example.project.util.UtilValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Calendar;
import java.util.List;

@RestController
@RequestMapping("/api")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @GetMapping("/payments")
    public List<Payment> list() {
        return paymentService.listPayment();
    }

    @GetMapping("/payments/{user_id}")
    public List<Payment> listPaymentId(@PathVariable Long user_id){
        return paymentService.findPaymentByUserId(user_id);
    }

    @GetMapping("/payments/")
    public List<Payment> listPaymentsByIdMonthAndYear(@RequestParam Long user_id, @RequestParam Integer month, @RequestParam Integer year){
        return paymentService.findPaymentByUserMonthAndYear(user_id, month, year);
    }

    @PostMapping("/payments")
    public ResponseEntity<String> create(@RequestBody PaymentRequest paymentRequest){

        validateRequest(paymentRequest);

        Double finalAmount = UtilConverter.currencyConverter(paymentRequest.getAmount(), paymentRequest.getCurrency());
        paymentRequest.setAmount(finalAmount);

        paymentService.savePayment(paymentRequest);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    private void validateRequest(PaymentRequest paymentRequest){
        if (UtilValidator.validateCurrency(paymentRequest.getCurrency())){
            throw new InvalidCurrencyException("Currency should be AR or USD.");
        }
        if (paymentRequest.getAmount() < 0){
            throw new InvalidAmountException("Amount should be greater than zero.");
        }
    }

}
