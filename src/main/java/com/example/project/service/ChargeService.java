package com.example.project.service;

import com.example.project.model.Charge;

import java.util.List;

public interface ChargeService {

    List<Charge> listCharges();

    List<Charge> findChargesByUserId(Long id);

    List<Charge> findChargesByUserIdAndMonth(Long id, Integer month);

    Double getTotalCharges(Long id);

    void saveCharge(Charge charge);

}
