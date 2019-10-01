package com.example.project.response;

import com.example.project.model.Charge;
import com.example.project.model.Payment;

import java.util.List;

public class UserStatusResponse {
    public UserStatusResponse(List<Charge> charges, List<Payment> payments, Double debt) {
        super();
        this.charges = charges;
        this.payments = payments;
        this.debt = debt;
    }


    private List<Charge> charges;
    private List<Payment> payments;
    private Double debt;

    public Double getDebt() {
        return debt;
    }

    public void setDebt(Double debt) {
        this.debt = debt;
    }

    public List<Charge> getCharges() {
        return charges;
    }

    public void setCharges(List<Charge> charges) {
        this.charges = charges;
    }

    public List<Payment> getPayments() {
        return payments;
    }

    public void setPayments(List<Payment> payments) {
        this.payments = payments;
    }

}
