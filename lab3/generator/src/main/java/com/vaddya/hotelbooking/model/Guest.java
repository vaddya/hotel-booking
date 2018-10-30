package com.vaddya.hotelbooking.model;

import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Guest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "reservation_id")
    private Reservation reservation;

    private String name;

    @Column(name = "is_child")
    private Boolean isChild;

    public Guest() {
    }

    public Guest(String name, Boolean isChild) {
        this.name = name;
        this.isChild = isChild;
    }

    public Long getId() {
        return id;
    }

    public Reservation getReservation() {
        return reservation;
    }

    public void setReservation(Reservation reservation) {
        this.reservation = reservation;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getChild() {
        return isChild;
    }

    public void setChild(Boolean child) {
        isChild = child;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Guest guest = (Guest) o;
        return Objects.equals(id, guest.id) &&
                Objects.equals(name, guest.name) &&
                Objects.equals(isChild, guest.isChild);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, isChild);
    }

    @Override
    public String toString() {
        return "Guest{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", isChild=" + isChild +
                '}';
    }
}
