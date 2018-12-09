package com.vaddya.hotelbooking.model;

import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "reservation_id")
    private Reservation reservation;

    private String advantages;

    private String disadvantages;

    private Short rating;

    public Review() {
    }

    public Review(String advantages, String disadvantages, Short rating) {
        this.advantages = advantages;
        this.disadvantages = disadvantages;
        this.rating = rating;
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

    public String getAdvantages() {
        return advantages;
    }

    public void setAdvantages(String advantages) {
        this.advantages = advantages;
    }

    public String getDisadvantages() {
        return disadvantages;
    }

    public void setDisadvantages(String disadvantages) {
        this.disadvantages = disadvantages;
    }

    public Short getRating() {
        return rating;
    }

    public void setRating(Short rating) {
        this.rating = rating;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Review review = (Review) o;
        return Objects.equals(id, review.id) &&
                Objects.equals(advantages, review.advantages) &&
                Objects.equals(disadvantages, review.disadvantages) &&
                Objects.equals(rating, review.rating);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, advantages, disadvantages, rating);
    }

    @Override
    public String toString() {
        return "Review{\n" +
                "id=" + id +
                ",\nadvantages='" + advantages + '\'' +
                ",\ndisadvantages='" + disadvantages + '\'' +
                ",\nrating=" + rating +
                "\n}";
    }
}
