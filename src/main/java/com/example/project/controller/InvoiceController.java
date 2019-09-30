package com.example.project.controller;

import com.example.project.model.Invoice;
import com.example.project.service.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class InvoiceController {

    @Autowired
    InvoiceService invoiceService;

    @GetMapping("/invoice")
    public Invoice getUserInvoiceByMonthAndYear(@RequestParam Long user_id, @RequestParam Integer month, @RequestParam Integer year) {
        return invoiceService.getUserInvoiceByMonthAndYear(user_id, month, year);
    }


}
