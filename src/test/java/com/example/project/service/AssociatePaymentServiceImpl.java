package com.example.project.service;


import com.example.project.ProjectApplication;
import com.example.project.model.Charge;
import com.example.project.model.Invoice;
import com.example.project.model.Payment;
import com.example.project.repository.InvoiceRepository;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ProjectApplication.class)
@SpringBootTest()
public class AssociatePaymentServiceImpl {

    @InjectMocks
    private AsociatePaymentService asociatePaymentService;

    @Mock
    private InvoiceRepository invoiceRepository;

    @Mock
    private AssociationTableService associationTableService;

    @Mock
    private InvoiceService invoiceService;

    @Mock
    private ChargeService chargeService;



    @Test
    public void testAssociate(){

        Payment payment = new Payment();
        payment.setDate(new Date());

        Charge charge1 = new Charge();
        Charge charge2 = new Charge();

        charge1.setDebt(50.0);
        charge1.setEventId(1L);
        charge1.setPaid_out(0);
        charge2.setDebt(50.0);
        charge2.setEventId(2L);
        charge2.setPaid_out(0);
        payment.setAmount(70.0);

        List<Charge> chargeList = Arrays.asList(charge1, charge2);

        when(chargeService.findChargesByUserIdMonthAndYearNotPaid(any(), any(), any())).thenReturn(chargeList);

        asociatePaymentService.associate(payment);

        Assertions.assertThat(charge1.getDebt()).isEqualTo(0.0);
        Assertions.assertThat(charge1.getPaid_out()).isEqualTo(1);
        Assertions.assertThat(charge2.getDebt()).isEqualTo(30.0);
        Assertions.assertThat(charge2.getPaid_out()).isEqualTo(0);
    }
}
