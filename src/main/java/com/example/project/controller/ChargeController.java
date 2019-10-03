package com.example.project.controller;

import com.example.project.request.ChargeRequest;
import com.example.project.service.ChargeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.project.model.Charge;

import javax.validation.Valid;
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

    @GetMapping("/charges/{user_id}")
    public List<Charge> listChargesById(@PathVariable Long user_id){
        return chargeService.findChargesByUserId(user_id);
    }

    @GetMapping("/bills/")
    public List<Charge> listChargesByIdMonthAndYear(@RequestParam Long user_id, @RequestParam Integer month, @RequestParam Integer year){
        return chargeService.findChargesByUserIdMonthAndYear(user_id, month, year);
    }

    @GetMapping("/debt/{user_id}")
    public List<Charge> listChargeNotPaidByUser(@PathVariable Long user_id){
        return chargeService.findChargesByUserIdNotPaid(user_id);
    }

    @PostMapping("/charges")
    public void insertCharge(@Valid @RequestBody ChargeRequest chargeRequest){ //Charge eventRequestBody){
        Charge charge = buildCharge(chargeRequest);
        chargeService.saveCharge(charge);
    }

    private Charge buildCharge(ChargeRequest chargeRequest){
        Charge charge = new Charge();

        charge.setDebt(chargeRequest.getDebt());
        charge.setPaid_out(chargeRequest.getPaid_out());
        charge.setInvoiceId(chargeRequest.getInvoiceId());
        charge.setEventType(chargeRequest.getEventType());
        charge.setEventId(chargeRequest.getEventId());
        charge.setCurrency(chargeRequest.getCurrency());
        charge.setAmount(chargeRequest.getAmount());
        charge.setUserId(chargeRequest.getUserId());
        charge.setDate(chargeRequest.getDate());

        return charge;
    }

}
