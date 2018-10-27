package com.vaddya.hotelbooking.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Hotel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    @ManyToOne
    @JoinColumn(name = "city_id")
    private City city;

    @ManyToOne
    @JoinColumn(name = "house_rules_id")
    private HouseRules houseRules;

    private String address;

    private short stars;

    private String description;

    public Hotel() {
    }

    public Hotel(String name, City city, HouseRules houseRules, String address, short stars, String description) {
        this.name = name;
        this.city = city;
        this.houseRules = houseRules;
        this.address = address;
        this.stars = stars;
        this.description = description;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public HouseRules getHouseRules() {
        return houseRules;
    }

    public void setHouseRules(HouseRules houseRules) {
        this.houseRules = houseRules;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public short getStars() {
        return stars;
    }

    public void setStars(short stars) {
        this.stars = stars;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Hotel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", city=" + city +
                ", houseRules=" + houseRules +
                ", address='" + address + '\'' +
                ", stars=" + stars +
                ", description='" + description + '\'' +
                '}';
    }
}
