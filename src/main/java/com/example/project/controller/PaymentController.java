package com.example.project.controller;


import com.example.project.model.Payment;
import com.example.project.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {

    @Autowired
    private PaymentRepository repository;

    @GetMapping
    public List<Payment> list() {
        return repository.findAll();
    }

    @GetMapping("/{user_id}")
    public List<Payment> listEventId(@PathVariable Long user_id){
        return repository.findByUserId(user_id);
    }

    @PostMapping
    public Payment insert(@Valid @RequestBody Payment payment){
        return repository.save(payment);
    }

    @PutMapping
    public Payment modify(@RequestBody Payment payment){
        return repository.save(payment);
    }

    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable("id") Long id){
        repository.deleteById(id);
    }
}
