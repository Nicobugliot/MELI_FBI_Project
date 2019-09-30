package com.example.project.service;

import com.example.project.model.AssociationTable;
import com.example.project.repository.AssociationTableRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AssociationTableServiceImpl implements AssociationTableService{

    @Autowired
    private AssociationTableRepository associationTableRepository;

    @Override
    public void saveAssociation(Long chargeId, Long paymentId){
        AssociationTable associationTable = new AssociationTable();
        associationTable.setChargeId(chargeId);
        associationTable.setPaymentId(paymentId);

        associationTableRepository.save(associationTable);
    }

}
