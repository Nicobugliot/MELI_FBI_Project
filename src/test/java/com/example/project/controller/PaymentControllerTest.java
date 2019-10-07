package com.example.project.controller;


import com.example.project.ProjectApplication;
import com.example.project.exception.InvalidAmountException;
import com.example.project.model.Payment;
import com.example.project.request.ChargeRequest;
import com.example.project.request.PaymentRequest;
import com.example.project.service.ChargeService;
import com.example.project.service.PaymentService;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.mockito.Mockito.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ProjectApplication.class)
@SpringBootTest()
public class PaymentControllerTest {

    @InjectMocks
    private PaymentController paymentController;

    @Mock
    private PaymentService paymentService;

    @Test
    public void testAddPaymentWithAmountExceptionController() {

        PaymentRequest paymentRequest = new PaymentRequest();

        paymentRequest.setAmount(-10.0);
        paymentRequest.setCurrency("AR");

        Assertions.assertThatThrownBy(() -> {
            paymentController.create(paymentRequest);
        }).isInstanceOf(InvalidAmountException.class).hasMessage("Amount should be greater than zero.");
    }

    @Test
    public void testAddPaymentController() {

        PaymentRequest paymentRequest = new PaymentRequest();

        paymentRequest.setAmount(10.0);
        paymentRequest.setCurrency("AR");

        paymentController.create(paymentRequest);

        verify(paymentService, times(1)).savePayment(any(), any(), any(), any(), any());
    }
}

