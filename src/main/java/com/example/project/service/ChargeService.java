package com.example.project.service;

import com.example.project.model.Charge;

import java.util.List;

public interface ChargeService {

    List<Charge> listCharges();

    List<Charge> findChargesByUserId(Long id);

    List<Charge> findChargesByUserIdAndMonth(Long id, Integer month);

    List<Charge> findChargesByUserIdMonthAndYearNotPaid(Long id, Integer month, Integer year);

    void saveCharge(Charge charge);

    void updateAllCharges(List<Charge> charge);

}
