package com.vaddya.hotelbooking.model;

import java.sql.Time;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "house_rules")
public class HouseRules {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "checkin_from")
    private Time checkinFrom;

    @Column(name = "checkout_until")
    private Time checkoutUntil;

    @Column(name = "cancellation_policy")
    private String cancellationPolicy;

    public HouseRules() {
    }

    public HouseRules(Time checkinFrom, Time checkoutUntil, String cancellationPolicy) {
        this.checkinFrom = checkinFrom;
        this.checkoutUntil = checkoutUntil;
        this.cancellationPolicy = cancellationPolicy;
    }

    public Integer getId() {
        return id;
    }

    public Time getCheckinFrom() {
        return checkinFrom;
    }

    public void setCheckinFrom(Time checkinFrom) {
        this.checkinFrom = checkinFrom;
    }

    public Time getCheckoutUntil() {
        return checkoutUntil;
    }

    public void setCheckoutUntil(Time checkoutUntil) {
        this.checkoutUntil = checkoutUntil;
    }

    public String getCancellationPolicy() {
        return cancellationPolicy;
    }

    public void setCancellationPolicy(String cancellationPolicy) {
        this.cancellationPolicy = cancellationPolicy;
    }

    @Override
    public String toString() {
        return "HouseRules{" +
                "id=" + id +
                ", checkinFrom=" + checkinFrom +
                ", checkoutUntil=" + checkoutUntil +
                ", cancellationPolicy='" + cancellationPolicy + '\'' +
                '}';
    }
}

