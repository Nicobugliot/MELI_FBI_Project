package com.example.project.service;

import com.example.project.exception.InvalidCurrencyException;
import com.example.project.exception.InvalidEventTypeException;
import com.example.project.model.Charge;
import com.example.project.repository.ChargeRepository;
import com.example.project.util.UtilValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChargeServiceImpl implements ChargeService {

    @Autowired
    private ChargeRepository chargeRepository;

    @Override
    public List<Charge> listCharges(){
        return chargeRepository.findAll();
    }

    @Override
    public List<Charge> findChargesByUserId(Long id) {
        return chargeRepository.findByUserId(id);
    }

    @Override
    public List<Charge> findChargesByUserIdAndMonth(Long id, Integer month) {
        return chargeRepository.findByUserIdAndMonth(id, month);
    }

    @Override
    public void saveCharge(Charge charge){
        if (UtilValidator.validateCurrency(charge.getCurrency())){
            throw new InvalidCurrencyException("Currency is wrong, the values accepted are 'USD' or 'AR'");
        }
        if (UtilValidator.validateEventType(charge.getEventType())){
            throw new InvalidEventTypeException("Event type is wrong");
        }
        chargeRepository.save(charge);
    }

}
