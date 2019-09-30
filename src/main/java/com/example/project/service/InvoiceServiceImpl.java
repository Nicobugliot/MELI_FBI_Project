package com.example.project.service;

import com.example.project.model.Invoice;
import com.example.project.model.Payment;
import com.example.project.repository.InvoiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InvoiceServiceImpl implements InvoiceService{

    @Autowired
    private InvoiceRepository invoiceRepository;

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
    public void saveInvoice(Invoice invoice){
        invoiceRepository.save(invoice);
    }




}
