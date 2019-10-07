package com.example.project.service;

import com.example.project.model.Charge;

import java.util.List;

public interface ChargeService {

    List<Charge> listCharges();

    List<Charge> findChargesByUserId(Long id);

    List<Charge> findChargesByUserIdMonthAndYear(Long id, Integer month, Integer year);

    List<Charge> findChargesByUserIdMonthAndYearNotPaid(Long id, Integer month, Integer year);

    List<Charge> findChargesByUserIdNotPaid(Long id);

    void saveCharge(Charge charge);

    void updateAllCharges(List<Charge> charge);

}
