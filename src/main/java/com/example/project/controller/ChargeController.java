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


    @PostMapping("/charges")
    public void insertCharge(@Valid @RequestBody ChargeRequest chargeRequest){ //Charge eventRequestBody){
        chargeService.saveCharge(buildCharge(chargeRequest));
    }

    private Charge buildCharge(ChargeRequest chargeRequest){
        Charge charge = new Charge();

        charge.setDebt(chargeRequest.getDebt());
        charge.setPaid_out(chargeRequest.getPaid_out());
        charge.setInvoiceId(chargeRequest.getInvoice_id());
        charge.setEventType(chargeRequest.getEvent_type());
        charge.setEventId(chargeRequest.getEvent_id());
        charge.setCurrency(chargeRequest.getCurrency());
        charge.setAmount(chargeRequest.getAmount());
        charge.setUserId(chargeRequest.getUser_id());
        charge.setDate(chargeRequest.getDate());
        return charge;
    }

}
