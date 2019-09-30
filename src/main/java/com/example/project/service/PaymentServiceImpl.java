package com.example.project.service;

import com.example.project.exception.InvalidCurrencyException;
import com.example.project.exception.InvalidEventTypeException;
import com.example.project.model.Charge;
import com.example.project.model.Invoice;
import com.example.project.model.Payment;
import com.example.project.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentServiceImpl implements PaymentService{

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private ChargeService chargeService;

    @Autowired
    private InvoiceService invoiceService;

    @Autowired
    private AssociationTableService associationTableService;

    @Override
    public List<Payment> listPayment(){
        return paymentRepository.findAll();
    }

    @Override
    public List<Payment> findPaymentByUserId(Long id){
        return paymentRepository.findByUserId(id);
    }

    @Override
    public void savePayment(Long user_id, Double amount, Integer month, Integer year, String currency){

        Invoice invoice = invoiceService.getUserInvoiceByMonthAndYear(user_id, month + 1, year);

        if (invoice == null){
            throw new InvalidEventTypeException("No existen cargos para este usuario");
        }

        if (validatePaymentAmount(amount, invoice)){
            throw new InvalidEventTypeException("Estas intentando pagar más de lo que te corresponde");
        }

        Payment payment = new Payment();
        payment.setAmount(amount);
        payment.setUserId(user_id);
        payment.setCurrency(currency);

        // Guardo el pago
        Payment userPayment = paymentRepository.save(payment);

        try{
            invoiceService.addPaymentToInvoice(invoice, amount);
        }catch (Exception e) {
            e.printStackTrace();
        }

        associatePaymentAsync(user_id, amount, month + 1, year, userPayment.getId());

    }

    private Boolean validatePaymentAmount(Double amount, Invoice invoice) {
        return ((invoice.getDebt() - amount) < 0);
    }

    private void associatePaymentAsync(Long user_id, Double amount, Integer month, Integer year, Long id){
        List<Charge> userCharges = chargeService.findChargesByUserIdMonthAndYearNotPaid(user_id, month, year);

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
                charge.setDebt( Math.abs(amount) );
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
