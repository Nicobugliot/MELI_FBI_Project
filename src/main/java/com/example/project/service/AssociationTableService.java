package com.example.project.service;

import com.example.project.model.AssociationTable;

import java.util.List;

public interface AssociationTableService {

    void saveAssociation(Long chargeId, Long paymentId);

    List<AssociationTable> getAssociationTable();

}
