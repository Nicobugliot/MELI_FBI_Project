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

    @GetMapping("/charges")
    public List<Charge> list(){
        return chargeService.listCharges();
    }

    @GetMapping("/charges/{user_id}")
    public List<Charge> listChargesById(@PathVariable Long user_id){
        return chargeService.findChargesByUserId(user_id);
    }

    @GetMapping("/bills/")
    public List<Charge> listChargesByIdAndMonth(@RequestParam Long user_id, @RequestParam Integer month){
        return chargeService.findChargesByUserIdAndMonth(user_id, month);
    }

    @PostMapping("/charges")
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
