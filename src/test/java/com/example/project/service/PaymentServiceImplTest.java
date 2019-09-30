package com.example.project.service;

import com.example.project.model.Invoice;
import com.example.project.repository.PaymentRepository;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class PaymentServiceImplTest {

    PaymentService paymentService;

    @Mock
    PaymentRepository paymentRepository;

    @Mock
    ChargeService chargeService;

    @Mock
    InvoiceService invoiceService;

    /*
    @Test
    public void paymentWithAmountBiggerThanInvoiceDeeb(){

        Invoice invoice = new Invoice();
        invoice.deuda = 200d;

        when(invoiceService.getCurrentInvoice(any())).thenReturn(invoice);

        Assertions.assertThatThrownBy(( )->{
            paymentService.savePayment(1L, 300d);
        }).isInstanceOf(Exception.class);

        verify(paymentRepository.save(any()),times(0));

    }

     */



}
