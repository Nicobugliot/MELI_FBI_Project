package com.example.project.controller;


import com.example.project.model.Payment;
import com.example.project.response.UserStatusResponse;
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

    @GetMapping("/payments/{user_id}")
    public List<Payment> listPaymentId(@PathVariable Long user_id){
        return paymentService.findPaymentByUserId(user_id);
    }

    @GetMapping("/status/")
    public UserStatusResponse userStatus(@RequestParam Long user_id, @RequestParam Integer month){
        return paymentService.getUserStatus(user_id, month);
    }

    @PostMapping("/payments")
    public void insert(@Valid @RequestBody Payment payment){
        paymentService.savePayment(payment);
    }

}
