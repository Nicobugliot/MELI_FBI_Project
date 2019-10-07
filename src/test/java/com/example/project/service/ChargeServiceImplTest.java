package com.example.project.service;

import com.example.project.ProjectApplication;
import com.example.project.controller.ChargeController;
import com.example.project.exception.InvalidCurrencyException;
import com.example.project.model.Charge;
import com.example.project.model.Invoice;
import com.example.project.repository.ChargeRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.util.AssertionErrors.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ProjectApplication.class)
@SpringBootTest()
public class ChargeServiceImplTest {

    @InjectMocks
    private ChargeServiceImpl chargeServiceImpl;

    @Mock
    private ChargeRepository chargeRepository;

    @Mock
    private InvoiceService invoiceService;


    @Test
    public void testRetrieveChargeWithMockRepository(){

        Charge charge = new Charge();

        List<Charge> chargeList = new ArrayList<>();
        chargeList.add(charge);

        when(chargeRepository.findByUserId(22L)).thenReturn(chargeList);
        Assert.assertTrue(chargeServiceImpl.findChargesByUserId(22L).contains(charge));
    }

    @Test
    public void testRetrieveChargeByMonthAndYear(){

        Charge charge = new Charge();

        List<Charge> chargeList = new ArrayList<>();
        chargeList.add(charge);

        when(chargeRepository.findByUserIdMonthAndYear(22L, 10, 2019)).thenReturn(chargeList);
        Assert.assertTrue(chargeServiceImpl.findChargesByUserIdMonthAndYear(22L, 10, 2019).contains(charge));
    }

    @Test
    public void testRetrieveChargeByMonthAndYearNotPaid(){

        Charge charge = new Charge();

        List<Charge> chargeList = new ArrayList<>();
        chargeList.add(charge);

        when(chargeRepository.findByUserIdMonthAndYearNotPaid(22L, 10, 2019)).thenReturn(chargeList);
        Assert.assertTrue(chargeServiceImpl.findChargesByUserIdMonthAndYearNotPaid(22L, 10, 2019).contains(charge));
    }

    @Test
    public void testRetrieveChargeByIdNotPaid(){

        Charge charge = new Charge();

        List<Charge> chargeList = new ArrayList<>();
        chargeList.add(charge);

        when(chargeRepository.findChargesByUserIdNotPaid(22L)).thenReturn(chargeList);
        Assert.assertTrue(chargeServiceImpl.findChargesByUserIdNotPaid(22L).contains(charge));
    }

    @Test
    public void testRetrieveChargeByEventId(){

        Charge charge = new Charge();

        List<Charge> chargeList = new ArrayList<>();
        chargeList.add(charge);

        when(chargeRepository.findAll()).thenReturn(chargeList);
        Assert.assertTrue(chargeServiceImpl.listCharges().contains(charge));
    }


    @Test
    public void testSaveChargeIfInvoiceExist(){

        Charge charge1 = new Charge();
        charge1.setEventId(1L);
        charge1.setUserId(1L);
        charge1.setAmount(10.0);
        charge1.setDate(new Date());
        charge1.setCurrency("AR");
        charge1.setEventType("VENTA");

        Invoice invoice = new Invoice();
        invoice.setDebt(10.0);
        invoice.setId(1L);

        when(invoiceService.getUserInvoiceByMonthAndYear(any(), any(), any())).thenReturn(invoice);
        when(invoiceService.saveInvoice(any())).thenReturn(invoice);

        chargeServiceImpl.saveCharge(charge1);

        verify(chargeRepository, times(1)).save(any());
        verify(invoiceService, times(1)).getUserInvoiceByMonthAndYear(any(), any(), any());
        verify(invoiceService, times(1)).saveInvoice(any());
    }

    @Test
    public void testSaveChargeIfInvoiceDontExist(){

        Charge charge1 = new Charge();
        charge1.setEventId(1L);
        charge1.setUserId(1L);
        charge1.setAmount(10.0);
        charge1.setDate(new Date());
        charge1.setCurrency("AR");
        charge1.setEventType("VENTA");

        Invoice invoice = new Invoice();
        invoice.setId(1L);

        when(invoiceService.getUserInvoiceByMonthAndYear(any(), any(), any())).thenThrow(new NullPointerException());
        when(invoiceService.saveInvoice(any())).thenReturn(invoice);

        chargeServiceImpl.saveCharge(charge1);

        verify(chargeRepository, times(1)).save(any());
        verify(invoiceService, times(1)).getUserInvoiceByMonthAndYear(any(), any(), any());
        verify(invoiceService, times(1)).saveInvoice(any());

    }

    @Test
    public void testSaveAllTheCharges(){
        Charge charge1 = new Charge();
        Charge charge2 = new Charge();

        List<Charge> chargeList = Arrays.asList(charge1, charge2);

        when(chargeRepository.saveAll(any())).thenReturn(chargeList);

        chargeServiceImpl.updateAllCharges(chargeList);

        verify(chargeRepository, times(1)).saveAll(any());
    }



}