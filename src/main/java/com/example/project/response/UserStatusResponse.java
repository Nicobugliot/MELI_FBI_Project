package com.example.project.response;

import com.example.project.model.Charge;
import com.example.project.model.Payment;

import java.util.List;

public class UserStatusResponse {
    public UserStatusResponse(List<Charge> charges, List<Payment> payments, java.lang.Double totalPayments, java.lang.Double totalCharges) {
        super();
        this.charges = charges;
        this.payments = payments;
        this.totalPayments = totalPayments;
        this.totalCharges = totalCharges;
        this.debt = totalCharges - totalPayments;
    }


    private List<Charge> charges;
    private List<Payment> payments;
    private java.lang.Double totalPayments;
    private java.lang.Double totalCharges;
    private java.lang.Double debt;

    public java.lang.Double getDebt() {
        return debt;
    }

    public void setDebt(java.lang.Double debt) {
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

    public java.lang.Double getTotalPayments() {
        return totalPayments;
    }

    public void setTotalPayments(java.lang.Double totalPayments) {
        this.totalPayments = totalPayments;
    }

    public java.lang.Double getTotalCharges() {
        return totalCharges;
    }

    public void setTotalCharges(java.lang.Double totalCharges) {
        this.totalCharges = totalCharges;
    }
}
