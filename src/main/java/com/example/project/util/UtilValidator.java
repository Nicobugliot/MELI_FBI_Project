package com.example.project.util;

import com.example.project.model.Event;

public class UtilValidator {

    public static Boolean validateCurrency(Event event){
        String currency = event.getCurrency();
        return !((currency.equals("USD")) || (currency.equals("AR")));
    }
}
