package com.example.project.service;

import com.example.project.model.Charge;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class AsociatePaymentService {

    @Autowired
    ChargeService chargeService;

    @Autowired
    AssociationTableService associationTableService;

    void associate(Long user_id, Double amount, Integer month, Integer year, Long id){

        List<Charge> userCharges = chargeService.findChargesByUserIdMonthAndYearNotPaid(user_id,
                month,
                year);

        // Actualizo los cargos
        updateChargeAndAssociate(userCharges, amount, id);

    }

    private void updateChargeAndAssociate(List<Charge> userCharges, Double amount, Long paymentId){
        
        for (Charge charge: userCharges) {
            amount -= charge.getDebt();

            associationTableService.saveAssociation(charge.getEventId(), paymentId);

            if (amount >= 0) {
                charge.setDebt(0.00);
                charge.setPaid_out(1);
            }
            else{
                charge.setDebt( Math.abs(amount) );
                break;
            }
        }
        // Le hago un update a todos los cargos afectados.
        chargeService.updateAllCharges(userCharges);
    }

}
