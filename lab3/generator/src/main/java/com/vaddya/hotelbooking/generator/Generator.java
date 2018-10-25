package com.vaddya.hotelbooking.generator;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import com.github.javafaker.Faker;

public class Generator {

    private static final Faker faker = new Faker();

    public static void main(String[] args) throws SQLException {
        String url = "jdbc:postgresql://localhost:5432/hotel_booking";
        Connection conn = DriverManager.getConnection(url, "postgres", "postgres");
        Statement st = conn.createStatement();
        PreparedStatement insertUser = conn.prepareStatement("INSERT INTO \"user\" VALUES (DEFAULT, ?, ?, ?, ?)");
        insertUser.setString(1, faker.name().username());
        insertUser.setString(2, faker.internet().emailAddress());
        insertUser.setString(3, faker.internet().password(64, 65).toUpperCase());
        insertUser.setString(4, faker.phoneNumber().cellPhone());
        insertUser.executeUpdate();
        st.close();
    }

}
