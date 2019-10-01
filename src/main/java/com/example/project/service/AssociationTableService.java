package com.example.project.service;

import com.example.project.model.Charge;
import com.example.project.model.Payment;

public interface AssociationTableService {

    void saveAssociation(Long chargeId, Long paymentId);

}
