package com.vaddya.hotelbooking.model;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.hibernate.annotations.ColumnTransformer;

@Entity
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "room_id")
    private Room room;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "`from`")
    private Date from;

    @Column(name = "`to`")
    private Date to;

    @ColumnTransformer(read = "price::money::numeric", write = "?::numeric::money")
    private BigDecimal price;

    @Column(name = "is_paid")
    private Boolean isPaid;

    @OneToMany(mappedBy = "reservation")
    private Set<Guest> guests;

    @OneToOne(mappedBy = "reservation")
    private Cancellation cancellation;

    @OneToOne(mappedBy = "reservation")
    private Review review;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "bonus_penalty_reservation",
            joinColumns = {@JoinColumn(name = "reservation_id")},
            inverseJoinColumns = {@JoinColumn(name = "bonus_penalty_id")}
    )
    private Set<BonusPenalty> bonusPenalties;

    public Reservation() {
        this.guests = new HashSet<>();
        this.bonusPenalties = new HashSet<>();
    }

    public Reservation(Room room, User user, Date from, Date to, BigDecimal price, Boolean isPaid) {
        this.room = room;
        this.user = user;
        this.from = from;
        this.to = to;
        this.price = price;
        this.isPaid = isPaid;
        this.guests = new HashSet<>();
        this.bonusPenalties = new HashSet<>();
    }

    public Long getId() {
        return id;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getFrom() {
        return from;
    }

    public void setFrom(Date from) {
        this.from = from;
    }

    public Date getTo() {
        return to;
    }

    public void setTo(Date to) {
        this.to = to;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Boolean getPaid() {
        return isPaid;
    }

    public void setPaid(Boolean paid) {
        isPaid = paid;
    }

    public Set<Guest> getGuests() {
        return guests;
    }

    public void addGuest(Guest guest) {
        guest.setReservation(this);
        this.guests.add(guest);
    }

    public void removeGuest(Guest guest) {
        guest.setReservation(null);
        this.guests.remove(guest);
    }

    public Cancellation getCancellation() {
        return cancellation;
    }

    public void setCancellation(Cancellation cancellation) {
        if (this.cancellation != null) {
            this.cancellation.setReservation(null);
        }
        cancellation.setReservation(this);
        this.cancellation = cancellation;
    }

    public Review getReview() {
        return review;
    }

    public void setReview(Review review) {
        if (this.review != null) {
            this.review.setReservation(null);
        }
        review.setReservation(this);
        this.review = review;
    }

    public Set<BonusPenalty> getBonusPenalties() {
        return bonusPenalties;
    }

    public void addBonusPenalty(BonusPenalty bonusPenalty) {
        this.bonusPenalties.add(bonusPenalty);
    }

    public void removeBonusPenalty(BonusPenalty bonusPenalty) {
        this.bonusPenalties.remove(bonusPenalty);
    }

    @Override
    public String toString() {
        return "Reservation{\n" +
                "id=" + id +
                ",\nroom=" + room +
                ",\nuser=" + user +
                ",\nfrom=" + from +
                ",\nto=" + to +
                ",\nprice=" + price +
                ",\nisPaid=" + isPaid +
                ",\nguests=" + guests +
                ",\ncancellation=" + cancellation +
                ",\nreview=" + review +
                ",\nbonusPenalties=" + bonusPenalties +
                "\n}";
    }
}