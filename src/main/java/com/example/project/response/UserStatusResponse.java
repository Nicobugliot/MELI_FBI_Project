package com.example.project.response;

import com.example.project.model.Charge;
import com.example.project.model.Payment;

import java.util.List;

public class UserStatusResponse {
    public UserStatusResponse(List<Charge> charges, List<Payment> payments, Double totalPayments, Double totalCharges) {
        super();
        this.charges = charges;
        this.payments = payments;
        this.totalPayments = totalPayments;
        this.totalCharges = totalCharges;
        this.debt = totalCharges - totalPayments;
    }


    private List<Charge> charges;
    private List<Payment> payments;
    private Double totalPayments;
    private Double totalCharges;
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

    public Double getTotalPayments() {
        return totalPayments;
    }

    public void setTotalPayments(Double totalPayments) {
        this.totalPayments = totalPayments;
    }

    public Double getTotalCharges() {
        return totalCharges;
    }

    public void setTotalCharges(Double totalCharges) {
        this.totalCharges = totalCharges;
    }
}
