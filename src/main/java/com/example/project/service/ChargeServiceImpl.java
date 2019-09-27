package com.example.project.service;

import com.example.project.exception.InvalidCurrencyException;
import com.example.project.model.Charge;
import com.example.project.repository.EventRepository;
import com.example.project.util.UtilValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChargeServiceImpl implements ChargeService {

    @Autowired
    private EventRepository repository;


    @Override
    public List<Charge> listCharges(){
        return repository.findAll();
    }

    @Override
    public List<Charge> findChargesByUserId(Long id) {
        return repository.findByUserId(id);
    }

    @Override
    public List<Charge> findChargesByUserIdAndMonth(Long id, Integer month) {
        return repository.findByUserIdAndMonth(id, month);
    }

    @Override
    public void saveCharge(Charge charge){
        if (UtilValidator.validateCurrency(charge)){
            throw new InvalidCurrencyException("Currency is wrong, the values accepted are USD or AR");
        }
        repository.save(charge);
    }

}
