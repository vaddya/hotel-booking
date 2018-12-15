package com.vaddya.hotelbooking.model;

import java.math.BigDecimal;
import java.sql.Date;
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

import org.hibernate.annotations.ColumnTransformer;

@Entity
public class Price {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "room_id")
    private Room room;

    @Column(name = "`from`")
    private Date from;

    @Column(name = "`to`")
    private Date to;

    @Column(columnDefinition = "money")
    @ColumnTransformer(read = "price::money::numeric", write = "?::numeric::money")
    private BigDecimal price;

    public Price() {
    }

    public Price(Date from, Date to, BigDecimal price) {
        this.from = from;
        this.to = to;
        this.price = price;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Price price1 = (Price) o;
        return Objects.equals(id, price1.id) &&
                Objects.equals(from, price1.from) &&
                Objects.equals(to, price1.to) &&
                Objects.equals(price, price1.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, from, to, price);
    }

    @Override
    public String toString() {
        return "Price{" +
                "id=" + id +
                ", from=" + from +
                ", to=" + to +
                ", price=" + price +
                '}';
    }
}

