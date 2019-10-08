package com.example.project.controller;

import com.example.project.ProjectApplication;
import com.example.project.model.Invoice;
import com.example.project.service.InvoiceService;
import org.assertj.core.api.Assertions;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ProjectApplication.class)
@SpringBootTest()
public class InvoiceControllerTest {

    @InjectMocks
    private InvoiceController invoiceController;

    @Mock
    private InvoiceService invoiceService;

    @Test
    public void testAddInvoice() {

        Invoice invoice = new Invoice();
        invoice.setId(30L);

        when(invoiceService.getUserInvoiceByMonthAndYear(any(), any(), any())).thenReturn(invoice);

        Invoice invoice2 = invoiceController.getUserInvoiceByMonthAndYear(22L, 2, 2);
        Assert.assertEquals(invoice.getId(), invoice2.getId());
    }
}
