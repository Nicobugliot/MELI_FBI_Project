package com.example.project.service;

import com.example.project.controller.PaymentController;
import com.example.project.model.Invoice;
import com.example.project.model.Payment;
import com.example.project.repository.ChargeRepository;
import com.example.project.repository.PaymentRepository;
import com.example.project.request.ChargeRequest;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


public class PaymentServiceImplTest {

    @Autowired
    PaymentService paymentService;

    @Mock
    PaymentRepository paymentRepository;

    @Mock
    ChargeService chargeService;

    @Mock
    InvoiceService invoiceService;
    /*
    @Test
    public void paymentWithAmountBiggerThanInvoiceDebt(){

        Invoice invoice = new Invoice();
        invoice.setDebt(200.0);

        when(invoiceService.getUserInvoiceByMonthAndYear(any(), any(), any())).thenReturn(invoice);

        Assertions.assertThatThrownBy(( )->{
            paymentService.savePayment(1L, 300.00, 1, 2, "ASD");
        }).isInstanceOf(Exception.class);

        verify(paymentRepository.save(any()),times(0));

    }

     */


}
