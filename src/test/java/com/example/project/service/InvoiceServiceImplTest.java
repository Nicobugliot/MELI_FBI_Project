package com.example.project.service;

import com.example.project.ProjectApplication;
import com.example.project.model.Invoice;
import com.example.project.repository.InvoiceRepository;
import org.assertj.core.api.Assertions;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ProjectApplication.class)
@SpringBootTest()
public class InvoiceServiceImplTest {

    @InjectMocks
    private InvoiceServiceImpl invoiceServiceImpl;

    @Mock
    private InvoiceRepository invoiceRepository;

    @Test
    public void testAddPaymentToInvoice(){

        Invoice invoice = new Invoice();
        invoice.setDebt(1.0);

        invoiceServiceImpl.addPaymentToInvoice(invoice, 0.5);

        verify(invoiceRepository, times(1)).save(any());

        Assertions.assertThat(invoice.getDebt()).isEqualTo(0.5);
    }

    @Test
    public void testInvoiceByMonthAndYear(){

        Invoice invoice = new Invoice();

        when(invoiceRepository.userInvoiceByMonthAndYear(any(), any(), any())).thenReturn(invoice);

        Assert.assertEquals(invoice, invoiceServiceImpl.getUserInvoiceByMonthAndYear(any(), any(), any()));
    }

    @Test
    public void testListAllInvoices(){

        Invoice invoice1 = new Invoice();
        Invoice invoice2 = new Invoice();

        List<Invoice> invoiceList = Arrays.asList(invoice1, invoice2);

        when(invoiceRepository.findAll()).thenReturn(invoiceList);

        Assert.assertEquals(invoiceList, invoiceServiceImpl.listInvoice());
    }

    @Test
    public void testSaveInvoice(){

        Invoice invoice = new Invoice();

        when(invoiceRepository.save(any())).thenReturn(invoice);

        Assert.assertEquals(invoice, invoiceServiceImpl.saveInvoice(invoice));
    }
}
