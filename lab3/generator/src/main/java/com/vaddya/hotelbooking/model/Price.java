package com.vaddya.hotelbooking.model;

import java.sql.Date;

import javax.persistence.Entity;
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

    @ManyToOne
    @JoinColumn(name = "room_type_id")
    private RoomType roomType;

    private Date from;

    private Date to;

    @ColumnTransformer(
            forColumn = "price",
            read = "price::money::numeric::float8",
            write = "?::float8::numeric::money"
    )
    private float price;

    public Price() {
    }

    public Price(RoomType roomType, Date from, Date to, float price) {
        this.roomType = roomType;
        this.from = from;
        this.to = to;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public RoomType getRoomType() {
        return roomType;
    }

    public void setRoomType(RoomType roomType) {
        this.roomType = roomType;
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

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Price{" +
                "id=" + id +
                ", roomType=" + roomType +
                ", from=" + from +
                ", to=" + to +
                ", price=" + price +
                '}';
    }
}

