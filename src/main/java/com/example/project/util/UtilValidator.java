package com.example.project.util;

import com.example.project.model.Charge;

import java.util.Arrays;
import java.util.List;

public class UtilValidator {

    public static Boolean validateCurrency(String currency){
        return !((currency.equals("USD")) || (currency.equals("AR")));
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
}
