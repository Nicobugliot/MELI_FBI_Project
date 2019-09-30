package com.example.project.service;

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
    public Payment savePayment(Long user_id, Double amount, Integer month, Integer year, String currency){

        Invoice invoice = invoiceService.getUserInvoiceByMonthAndYear(user_id, month + 1, year);

        //validatePaymentAmount(amount, invoice);

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
            System.out.println("Tiro error");
        }

        associatePaymentAsync(user_id, amount, month + 1, year, userPayment.getId());

        return payment;
    }

    private Boolean validatePaymentAmount(Double amount, Invoice invoice) {
        return ((invoice.getDebt() - amount) < 0);
    }

    private void associatePaymentAsync(Long user_id, Double amount, Integer month, Integer year, Long id){
        List<Charge> userCharges = chargeService.findChargesByUserIdAndMonth(user_id, month);

        // Actualizo los cargos
        updateChargeAndAssociate(userCharges, amount, id);
    }

    private void updateChargeAndAssociate(List<Charge> userCharges, Double amount, Long paymentId){
        for (Charge charge: userCharges) {
            amount -= charge.getDebt();
            if (amount <= 0) {
                charge.setDebt( Math.abs(amount) );
                associationTableService.saveAssociation(charge.getEventId(), paymentId);
                break;
            }
            else{
                charge.setDebt(0.00);
                charge.setPaid_out(1);
                associationTableService.saveAssociation(charge.getEventId(), paymentId);
            }
        }
        chargeService.updateAllCharges(userCharges);
    }

}
