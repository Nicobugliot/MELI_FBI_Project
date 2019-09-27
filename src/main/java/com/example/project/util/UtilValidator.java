package com.example.project.util;

import com.example.project.model.Charge;

public class UtilValidator {

    public static Boolean validateCurrency(Charge charge){
        String currency = charge.getCurrency();
        return !((currency.equals("USD")) || (currency.equals("AR")));
    }
}
