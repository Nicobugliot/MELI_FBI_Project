package com.example.project.util;

public class UtilConverter {

    static Double COTIZACION_DOLAR = 58.5;

    public static Double currencyConverter(Double amount, String currency){
        if (currency.equals("USD")){
            return (amount * COTIZACION_DOLAR);
        }
        return amount;
    }
}
