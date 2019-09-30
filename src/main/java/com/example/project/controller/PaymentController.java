package com.example.project.controller;


import com.example.project.model.Payment;
import com.example.project.request.PaymentRequest;
import com.example.project.service.PaymentService;
import com.example.project.util.UtilValidator;
import org.springframework.beans.factory.annotation.Autowired;
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


    @PostMapping("/payments")
    public Payment create(@RequestBody PaymentRequest paymentRequest){

        // Chequeo que los campos sean válidos.
        try{
            validateRequest(paymentRequest);
        } catch (Exception e) {
            return new Payment();
        }

        // Chequeo que se pueda guardar en base de datos.
        try {
            return paymentService.savePayment(paymentRequest.getUserId(),
                    paymentRequest.getAmount(),
                    Calendar.getInstance().get(Calendar.MONTH),
                    Calendar.getInstance().get(Calendar.YEAR),
                    paymentRequest.getCurrency());

        } catch(Exception ignored){
            return new Payment();
        }
    }

    private void validateRequest(PaymentRequest paymentRequest) throws Exception {
        if (UtilValidator.validateCurrency(paymentRequest.getCurrency())) throw new Exception("INVALID CURRENCY");
        if (paymentRequest.getAmount() < 0) throw new Exception("INVALID AMOUNT");
    }

}
