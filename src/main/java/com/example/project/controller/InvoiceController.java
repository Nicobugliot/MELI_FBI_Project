package com.example.project.controller;

import com.example.project.model.Invoice;
import com.example.project.response.UserStatusResponse;
import com.example.project.service.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class InvoiceController {

    @Autowired
    InvoiceService invoiceService;

    @GetMapping("/invoice/")
    public Invoice getUserInvoiceByMonthAndYear(@RequestParam Long user_id, @RequestParam Integer month, @RequestParam Integer year) {
        return invoiceService.getUserInvoiceByMonthAndYear(user_id, month, year);
    }

    @GetMapping("/status/")
    public UserStatusResponse getUserStatus(@RequestParam Long user_id, @RequestParam Integer month, @RequestParam Integer year){
        return invoiceService.getUserStatus(user_id, month, year);
    }

    @GetMapping("/invoice")
    public List<Invoice> getInvoice() {
        return invoiceService.listInvoice();
    }


}
