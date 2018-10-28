package com.vaddya.hotelbooking.model;

import java.math.BigDecimal;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.ColumnTransformer;

@Entity
@Table(name = "bonus_penalty")
public class BonusPenalty {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String condition;

    @ColumnTransformer(read = "price::money::numeric", write = "?::numeric::money")
    private BigDecimal price;

    public BonusPenalty() {
    }

    public BonusPenalty(String condition, BigDecimal price) {
        this.condition = condition;
        this.price = price;
    }

    public Integer getId() {
        return id;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
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
        BonusPenalty that = (BonusPenalty) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(condition, that.condition) &&
                Objects.equals(price, that.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, condition, price);
    }

    @Override
    public String toString() {
        return "BonusPenalty{" +
                "id=" + id +
                ", condition='" + condition + '\'' +
                ", price=" + price +
                '}';
    }
}
