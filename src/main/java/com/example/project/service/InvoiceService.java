package com.example.project.service;

import com.example.project.model.Invoice;
import com.example.project.response.UserStatusResponse;

import java.util.List;


public interface InvoiceService {
    
    Invoice getUserInvoiceByMonthAndYear(Long user_id, Integer month, Integer year);

    void addPaymentToInvoice(Invoice invoice, Double payment);

    Invoice saveInvoice(Invoice invoice);

    List<Invoice> listInvoice();

    UserStatusResponse getUserStatus(Long user_id, Integer month, Integer year);
}
