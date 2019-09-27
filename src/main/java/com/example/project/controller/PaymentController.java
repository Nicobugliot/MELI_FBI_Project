package com.example.project.controller;


import com.example.project.model.Payment;
import com.example.project.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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

    @GetMapping("payments/{user_id}")
    public List<Payment> listEventId(@PathVariable Long user_id){
        return paymentService.findPaymentByUserId(user_id);
    }

    @PostMapping("/payments")
    public void insert(@Valid @RequestBody Payment payment){
        paymentService.savePayment(payment);
    }

}
