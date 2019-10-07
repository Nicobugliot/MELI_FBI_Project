package com.example.project.service;


import com.example.project.ProjectApplication;
import com.example.project.exception.InvalidAmountException;
import com.example.project.exception.InvalidEventTypeException;
import com.example.project.model.Invoice;
import com.example.project.model.Payment;
import com.example.project.repository.PaymentRepository;
import org.assertj.core.api.Assertions;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ProjectApplication.class)
@SpringBootTest()
public class PaymentServiceImplTest {

    @InjectMocks
    private PaymentServiceImpl paymentServiceImpl;

    @Mock
    private InvoiceService invoiceService;

    @Mock
    private ChargeService chargeService;

    @Mock
    private PaymentRepository paymentRepository;

    @Mock
    private AsociatePaymentService asociatePaymentService;

    @Test
    public void testRetrievePaymentWithMockRepository(){

        Payment payment = new Payment();

        List<Payment> paymentList = new ArrayList<>();
        paymentList.add(payment);

        when(paymentRepository.findByUserId(22L)).thenReturn(paymentList);
        Assert.assertTrue(paymentServiceImpl.findPaymentByUserId(22L).contains(payment));
    }

    @Test
    public void testRetrievePaymentByUserMonthAndYear(){

        Payment payment = new Payment();

        List<Payment> paymentList = new ArrayList<>();
        paymentList.add(payment);

        when(paymentRepository.findPaymentByUserMonthAndYear(any(), any(), any())).thenReturn(paymentList);
        Assert.assertTrue(paymentServiceImpl.findPaymentByUserMonthAndYear(any(), any(), any()).contains(payment));
    }

    @Test
    public void testRetrieveAllPayment(){

        Payment payment = new Payment();

        List<Payment> paymentList = new ArrayList<>();
        paymentList.add(payment);

        when(paymentRepository.findAll()).thenReturn(paymentList);
        Assert.assertTrue(paymentServiceImpl.listPayment().contains(payment));
    }

    @Test
    public void testPaymentAmount(){

        Long user_id = 11L;
        Double amount = 100.0;
        Integer month = 4;
        Integer year = 2019;
        String currency = "AR";

        Invoice invoice = new Invoice();
        invoice.setDebt(99.0);

        when(invoiceService.getUserInvoiceByMonthAndYear(any(), any(), any())).thenReturn(invoice);

        Assertions.assertThatThrownBy(() -> {
            paymentServiceImpl.savePayment(user_id, amount, month, year, currency);
        }).isInstanceOf(InvalidAmountException.class).hasMessage("Estas intentando pagar más de lo que te corresponde");

    }

    @Test
    public void testPaymentAmountLessThanInvoiceDebt(){

        Long user_id = 11L;
        Double amount = 100.0;
        Integer month = 4;
        Integer year = 2019;
        String currency = "AR";

        Invoice invoice = new Invoice();
        invoice.setDebt(200.0);

        when(invoiceService.getUserInvoiceByMonthAndYear(any(), any(), any())).thenReturn(invoice);

        paymentServiceImpl.savePayment(user_id, amount, month, year, currency);

        verify(paymentRepository, times(1)).save(any());
        verify(invoiceService, times(1)).addPaymentToInvoice(any(), any());

    }

    @Test
    public void testPaymentWithNotInvoice(){

        Long user_id = 11L;
        Double amount = 100.0;
        Integer month = 4;
        Integer year = 2019;
        String currency = "AR";

        when(invoiceService.getUserInvoiceByMonthAndYear(any(), any(), any())).thenReturn(null);

        Assertions.assertThatThrownBy(() -> {
            paymentServiceImpl.savePayment(user_id, amount, month, year, currency);
        }).isInstanceOf(InvalidEventTypeException.class).hasMessage("No existen cargos para este usuario");

    }

}
