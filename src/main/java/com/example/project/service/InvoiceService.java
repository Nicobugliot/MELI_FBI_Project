package com.example.project.service;

import com.example.project.model.Invoice;
import com.example.project.model.Payment;


public interface InvoiceService {
    
    Invoice getUserInvoiceByMonthAndYear(Long user_id, Integer month, Integer year);

    void addPaymentToInvoice(Invoice invoice, Double payment);

    Invoice saveInvoice(Invoice invoice);
}
