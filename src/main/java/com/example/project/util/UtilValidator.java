package com.example.project.util;

import com.example.project.model.Payment;

import java.util.Arrays;
import java.util.List;

public class UtilValidator {

    public static Boolean validateCurrency(String currency){
        List<String> currencies = Arrays.asList("USD", "AR");
        return !currencies.contains(currency);
    }

    public static Boolean validateEventType(String eventType){
        List<String> eventTypes = Arrays.asList("CLASIFICADO",
                "VENTA",
                "ENVIO",
                "CREDITO",
                "FIDELIDAD",
                "PUBLICIDAD");

        return !eventTypes.contains(eventType);
    }

    public static Boolean validatePayment(Double totalCharges, Double totalPayment, Payment payment){
        return (totalCharges - (totalPayment + payment.getAmount()) < 0);
    }
}
