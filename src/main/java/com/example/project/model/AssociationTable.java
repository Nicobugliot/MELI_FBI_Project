package com.example.project.model;


import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Entity
@Table(name = "association")
@EntityListeners(AuditingEntityListener.class)
public class AssociationTable {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "charge_id")
    private Long chargeId;

    @Column(name = "payment_id")
    private Long paymentId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getChargeId() {
        return chargeId;
    }

    public void setChargeId(Long chargeId) {
        this.chargeId = chargeId;
    }

    public Long getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(Long paymentId) {
        this.paymentId = paymentId;
    }
}
