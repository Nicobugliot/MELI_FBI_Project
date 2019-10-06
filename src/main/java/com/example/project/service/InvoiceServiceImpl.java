package com.example.project.service;

import com.example.project.model.Charge;
import com.example.project.model.Invoice;
import com.example.project.model.Payment;
import com.example.project.repository.InvoiceRepository;
import com.example.project.response.UserStatusResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InvoiceServiceImpl implements InvoiceService{

    @Autowired
    private InvoiceRepository invoiceRepository;

    @Autowired
    private ChargeService chargeService;

    @Autowired
    private PaymentService paymentService;

    @Override
    public List<Invoice> listInvoice(){
        return invoiceRepository.findAll();
    }


    @Override
    public UserStatusResponse getUserStatus(Long user_id, Integer month, Integer year) {
        System.out.println("ASD");
        List<Charge> userCharge = chargeService.findChargesByUserIdMonthAndYear(user_id, month, year);
        List<Payment> userPayment = paymentService.findPaymentByUserMonthAndYear(user_id, month, year);
        Invoice userInvoice = invoiceRepository.userInvoiceByMonthAndYear(user_id, month, year);

        return new UserStatusResponse(userCharge, userPayment, userInvoice.getDebt());

        // Implementar el estado del usuario ( cargos, pagos y deuda )
    }

    @Override
    public Invoice getUserInvoiceByMonthAndYear(Long user_id, Integer month, Integer year){
        return invoiceRepository.userInvoiceByMonthAndYear(user_id, month, year);
    }

    @Override
    public void addPaymentToInvoice(Invoice invoice, Double amount) {
        invoice.setDebt( invoice.getDebt() - amount );
        invoiceRepository.save(invoice);
    }

    @Override
    public Invoice saveInvoice(Invoice invoice){
        return invoiceRepository.save(invoice);
    }

    @Override
    public void deleteInvoiceById(Long id){
        invoiceRepository.deleteById(id);
    }

}
