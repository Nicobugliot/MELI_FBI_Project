package com.example.project.service;

import com.example.project.model.Charge;
import com.example.project.model.Payment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

@Service
public class AsociatePaymentService {

    @Autowired
    ChargeService chargeService;

    @Autowired
    AssociationTableService associationTableService;

    void associate(Payment payment){
        Date date = payment.getDate();
        LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

        Integer month = localDate.getMonthValue();
        Integer year = localDate.getYear();

        List<Charge> userCharges = chargeService.findChargesByUserIdMonthAndYearNotPaid(payment.getUserId(),
                month,
                year);

        // Actualizo los cargos
        updateChargeAndAssociate(userCharges, payment);

    }

    private void updateChargeAndAssociate(List<Charge> userCharges, Payment payment){
        // Acá le tendría que sumar al monto lo que tiene de sobra el usuario.
        Double amount = payment.getAmount();
        Long paymentId = payment.getId();

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

        // Agrego plata a favor del usuario si el monto es mayor a 0.

        // Le hago un update a todos los cargos afectados.
        chargeService.updateAllCharges(userCharges);
    }
}
