package com.example.project.model;


import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Entity
@Table(name = "invoice")
@EntityListeners(AuditingEntityListener.class)
public class Invoice {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "month")
    private Integer month;

    @Column(name = "year")
    private Integer year;

    @Column(name = "debt")
    private Double debt;

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Integer getMonth() {
        return month;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }

    public Double getDebt() {
        return debt;
    }

    public void setDebt(Double debt) {
        this.debt = debt;
    }
}
