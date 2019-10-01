package com.example.project.service;

import com.example.project.exception.InvalidEventTypeException;
import com.example.project.model.Invoice;
import com.example.project.model.Payment;
import com.example.project.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentServiceImpl implements PaymentService{

    @Autowired
    private AsociatePaymentService asociatePaymentService;

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

        new Thread(() -> {
            asociatePaymentService.associate(user_id, amount, month, year, id);
        }).start();
    }

}
