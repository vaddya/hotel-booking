package com.vaddya.hotelbooking.generator;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import com.github.javafaker.Faker;
import com.vaddya.hotelbooking.model.BonusPenalty;
import com.vaddya.hotelbooking.model.Cancellation;
import com.vaddya.hotelbooking.model.City;
import com.vaddya.hotelbooking.model.Country;
import com.vaddya.hotelbooking.model.Facility;
import com.vaddya.hotelbooking.model.Guest;
import com.vaddya.hotelbooking.model.Hotel;
import com.vaddya.hotelbooking.model.HouseRules;
import com.vaddya.hotelbooking.model.Price;
import com.vaddya.hotelbooking.model.Reservation;
import com.vaddya.hotelbooking.model.Review;
import com.vaddya.hotelbooking.model.Room;
import com.vaddya.hotelbooking.model.RoomType;
import com.vaddya.hotelbooking.model.User;
import com.vaddya.hotelbooking.utils.DateUtils;

public class EntityGenerator {

    private static Faker faker = new Faker(new Locale("ru"));

    private static BigDecimal randomPrice() {
        return BigDecimal.valueOf(faker.random().nextLong(15000));
    }

    private static Short randomStars() {
        return faker.random().nextInt(1, 5).shortValue();
    }

    public static BonusPenalty bonusPenalty() {
        return new BonusPenalty(
                faker.lorem().sentence(10),
                randomPrice()
        );
    }

    public static Cancellation cancellation() {
        return new Cancellation(
                faker.gameOfThrones().dragon().toUpperCase()
        );
    }

    public static City city(Country country) {
        return new City(
                country,
                faker.address().city()
        );
    }

    public static Country country() {
        return new Country(
                (faker.address().countryCode() + "X").toCharArray(),
                faker.address().country()
        );
    }

    public static Facility facility() {
        return new Facility(
                faker.book().title()
        );
    }

    public static Guest guest() {
        return new Guest(
                faker.name().name(),
                faker.bool().bool()
        );
    }

    public static Hotel hotel(City city, HouseRules houseRules) {
        return new Hotel(
                faker.company().name(),
                city,
                houseRules,
                faker.address().streetAddress(),
                randomStars(),
                faker.lorem().paragraph()
        );
    }

    public static HouseRules houseRules() {
        Calendar cal = Calendar.getInstance();
        cal.set(2018, Calendar.JANUARY, 1, 9, 0);
        Date from = cal.getTime();
        cal.add(Calendar.HOUR, 3);
        Date to = cal.getTime();
        Date checkoutUntil = faker.date().between(from, to);
        checkoutUntil.setMinutes(0);
        checkoutUntil.setSeconds(0);
        Date checkinFrom = faker.date().future(6, TimeUnit.HOURS, checkoutUntil);
        checkinFrom.setMinutes(0);
        checkinFrom.setSeconds(0);
        return new HouseRules(
                DateUtils.toSqlTime(checkinFrom),
                DateUtils.toSqlTime(checkoutUntil),
                faker.lorem().sentence(10)
        );
    }

    public static Price price() {
        Calendar cal = Calendar.getInstance();
        cal.set(2018, Calendar.JANUARY, 1);
        Date from = cal.getTime();
        cal.add(Calendar.MONTH, 24);
        Date to = cal.getTime();
        Date randomFrom = faker.date().between(from, to);
        Date randomTo = faker.date().future(20, TimeUnit.DAYS, randomFrom);
        return new Price(
                DateUtils.toSqlDate(randomFrom),
                DateUtils.toSqlDate(randomTo),
                randomPrice()
        );
    }

    public static Reservation reservation(Room room, User user) {
        Calendar cal = Calendar.getInstance();
        cal.set(2018, Calendar.JANUARY, 1);
        Date from = cal.getTime();
        cal.add(Calendar.MONTH, 11);
        Date to = cal.getTime();
        Date randomFrom = faker.date().between(from, to);
        Date randomTo = faker.date().future(20, TimeUnit.DAYS, randomFrom);
        return new Reservation(
                room,
                user,
                DateUtils.toSqlDate(randomFrom),
                DateUtils.toSqlDate(randomTo),
                randomPrice(),
                faker.bool().bool()
        );
    }

    public static Review review() {
        return new Review(
                faker.lorem().paragraph(2),
                faker.lorem().paragraph(2),
                randomStars()
        );
    }

    public static Room room(RoomType roomType) {
        return new Room(
                roomType,
                faker.gameOfThrones().house()
        );
    }

    public static RoomType roomType(Hotel hotel, Set<Facility> facilities) {
        return new RoomType(
                hotel,
                faker.ancient().god(),
                randomStars(),
                faker.lorem().sentence(10),
                facilities
        );
    }

    public static User user(City city) {
        return new User(
                faker.name().username(),
                faker.internet().emailAddress(),
                faker.internet().password(64, 65).toUpperCase().toCharArray(),
                faker.phoneNumber().cellPhone(),
                city
        );
    }
}
