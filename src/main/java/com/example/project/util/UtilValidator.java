package com.example.project.util;

import com.example.project.model.Charge;

public class UtilValidator {

    public static Boolean validateCurrency(String currency){
        return !((currency.equals("USD")) || (currency.equals("AR")));
    }
}
