package com.example.project.controller;

import com.example.project.ProjectApplication;
import com.example.project.exception.InvalidAmountException;
import com.example.project.exception.InvalidCurrencyException;
import com.example.project.exception.InvalidEventTypeException;
import com.example.project.request.ChargeRequest;
import com.example.project.service.ChargeService;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ProjectApplication.class)
@SpringBootTest()
public class ChargeControllerTest {

    @InjectMocks
    private ChargeController chargeController;

    @Mock
    private ChargeService chargeService;

    @Test
    public void testAddChargeInvalidAmountExceptionController() {

        ChargeRequest chargeRequest = new ChargeRequest();
        chargeRequest.setAmount(-10.0);
        chargeRequest.setCurrency("AR");
        chargeRequest.setEventType("VENTA");


        Assertions.assertThatThrownBy(() -> {
            chargeController.insertCharge(chargeRequest);
        }).isInstanceOf(InvalidAmountException.class).hasMessage("Amount should be greater than zero.");
    }

    @Test
    public void testAddChargeInvalidCurrencyExceptionController() {

        ChargeRequest chargeRequest = new ChargeRequest();
        chargeRequest.setAmount(10.0);
        chargeRequest.setCurrency("EU");
        chargeRequest.setEventType("VENTA");


        Assertions.assertThatThrownBy(() -> {
            chargeController.insertCharge(chargeRequest);
        }).isInstanceOf(InvalidCurrencyException.class).hasMessage("Currency should be AR or USD.");
    }

    @Test
    public void testAddChargeInvalidEventTypeExceptionController() {

        ChargeRequest chargeRequest = new ChargeRequest();
        chargeRequest.setAmount(10.0);
        chargeRequest.setCurrency("AR");
        chargeRequest.setEventType("EVENTO NO INCLUIDO");


        Assertions.assertThatThrownBy(() -> {
            chargeController.insertCharge(chargeRequest);
        }).isInstanceOf(InvalidEventTypeException.class).hasMessage("Event type is wrong");
    }

    @Test
    public void testSaveChargeController() {

        ChargeRequest chargeRequest = new ChargeRequest();
        chargeRequest.setAmount(10.0);
        chargeRequest.setCurrency("AR");
        chargeRequest.setEventType("VENTA");

        chargeController.insertCharge(chargeRequest);

        verify(chargeService, times(1)).saveCharge(any());
    }
}
