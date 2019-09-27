package com.example.project.controller;

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

    @GetMapping("/events")
    public List<Charge> list(){
        return chargeService.listCharges();
    }

    @GetMapping("/events/{user_id}")
    public List<Charge> listChargesById(@PathVariable Long user_id){
        return chargeService.findChargesByUserId(user_id);
    }

    @GetMapping("/events/")
    public List<Charge> listChargesByIdAndMonth(@PathVariable Long user_id, @PathVariable Integer month){
        return chargeService.findChargesByUserIdAndMonth(user_id, month);
    }

    @PostMapping("/events")
    public void insertCharge(@Valid @RequestBody Charge charge){
        chargeService.saveCharge(charge);
    }

    /*
    @PutMapping
    public Event modify(@RequestBody Event event){
        return repository.save(event);
    }

    @DeleteMapping(value = "/{event_id}")
    public void delete(@PathVariable("event_id") Long id){
        repository.deleteById(id);
    }
     */

}
