package com.vaddya.hotelbooking.model;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "room_type")
public class RoomType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "hotel_id")
    private Hotel hotel;

    private String type;

    private short capacity;

    private String description;

    @ManyToMany
    @JoinTable(
            name = "room_facility",
            joinColumns = {@JoinColumn(name = "room_type_id")},
            inverseJoinColumns = {@JoinColumn(name = "facility_id")}
    )
    private Set<Facility> facilities;

    public RoomType() {
    }

    public RoomType(Hotel hotel, String type, short capacity, String description, Set<Facility> facilities) {
        this.hotel = hotel;
        this.type = type;
        this.capacity = capacity;
        this.description = description;
        this.facilities = facilities;
    }

    public Long getId() {
        return id;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public short getCapacity() {
        return capacity;
    }

    public void setCapacity(short capacity) {
        this.capacity = capacity;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<Facility> getFacilities() {
        return facilities;
    }

    public void setFacilities(Set<Facility> facilities) {
        this.facilities = facilities;
    }

    @Override
    public String toString() {
        return "RoomType{" +
                "id=" + id +
                ", hotel=" + hotel +
                ", type='" + type + '\'' +
                ", capacity=" + capacity +
                ", description='" + description + '\'' +
                ", facilities=" + facilities +
                '}';
    }
}
