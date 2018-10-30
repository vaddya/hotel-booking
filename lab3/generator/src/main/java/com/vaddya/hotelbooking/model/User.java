package com.vaddya.hotelbooking.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "\"user\"")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "user_id_seq")
    @SequenceGenerator(name="user_id_seq", sequenceName="user_id_seq", allocationSize=1)
    private Integer id;

    private String name;

    private String email;

    @Column(name = "password_hash")
    private char[] passwordHash;

    @Column(name = "phone_number")
    private String phoneNumber;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "city_id")
    private City city;

    public User() {
    }

    public User(String name, String email, char[] passwordHash, String phoneNumber, City city) {
        this.name = name;
        this.email = email;
        this.passwordHash = passwordHash;
        this.phoneNumber = phoneNumber;
        this.city = city;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public char[] getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(char[] passwordHash) {
        this.passwordHash = passwordHash;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    @Override
    public String toString() {
        return "User{\n" +
                "id=" + id +
                ",\nname='" + name + '\'' +
                ",\nemail='" + email + '\'' +
                ",\npasswordHash=" + new String(passwordHash) +
                ",\nphoneNumber='" + phoneNumber + '\'' +
                ",\ncity=" + city +
                "\n}";
    }
}
