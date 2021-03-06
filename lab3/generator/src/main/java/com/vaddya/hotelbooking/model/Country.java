package com.vaddya.hotelbooking.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Country {

    @Id
    @Column(columnDefinition = "bpchar")
    private char[] id;

    private String name;

    public Country() {
    }

    public Country(char[] id, String name) {
        this.id = id;
        this.name = name;
    }

    public char[] getId() {
        return id;
    }

    public void setId(char[] id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Country{" +
                "id=" + new String(id) +
                ", name='" + name + '\'' +
                '}';
    }
}
