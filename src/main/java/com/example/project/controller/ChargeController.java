package com.example.project.controller;

import com.example.project.exception.InvalidAmountException;
import com.example.project.exception.InvalidCurrencyException;
import com.example.project.exception.InvalidEventTypeException;
import com.example.project.request.ChargeRequest;
import com.example.project.service.ChargeService;
import com.example.project.util.UtilValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import com.example.project.model.Charge;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ChargeController {

    @Autowired
    private ChargeService chargeService;

    @GetMapping("/charges")
    public List<Charge> list(){
        return chargeService.listCharges();
    }

    @GetMapping("/user/{user_id}/charges")
    public List<Charge> listChargesById(@PathVariable Long user_id){
        return chargeService.findChargesByUserId(user_id);
    }

    @GetMapping("/charges/")
    public List<Charge> listChargesByIdMonthAndYear(@RequestParam Long user_id, @RequestParam Integer month, @RequestParam Integer year){
        return chargeService.findChargesByUserIdMonthAndYear(user_id, month, year);
    }

    @GetMapping("/user/{user_id}/debt")
    public List<Charge> listChargeNotPaidByUser(@PathVariable Long user_id){
        return chargeService.findChargesByUserIdNotPaid(user_id);
    }

    @PostMapping("/charges")
    public ResponseEntity<String> insertCharge(@Validated @RequestBody ChargeRequest chargeRequest){

        validateChargeFields(chargeRequest);

        Charge charge = buildCharge(chargeRequest);
        chargeService.saveCharge(charge);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    private Charge buildCharge(ChargeRequest chargeRequest){
        Charge charge = new Charge();

        charge.setEventType(chargeRequest.getEventType());
        charge.setEventId(chargeRequest.getEventId());
        charge.setCurrency(chargeRequest.getCurrency());
        charge.setAmount(chargeRequest.getAmount());
        charge.setUserId(chargeRequest.getUserId());
        charge.setDate(chargeRequest.getDate());

        return charge;
    }

    private void validateChargeFields(ChargeRequest chargeRequest){
        if (UtilValidator.validateCurrency(chargeRequest.getCurrency())) {
            throw new InvalidCurrencyException("Currency should be AR or USD.");
        }
        if (chargeRequest.getAmount() < 0) {
            throw new InvalidAmountException("Amount should be greater than zero.");
        }
        if (UtilValidator.validateEventType(chargeRequest.getEventType())){
            throw new InvalidEventTypeException("Event type is wrong");
        }
    }

}
