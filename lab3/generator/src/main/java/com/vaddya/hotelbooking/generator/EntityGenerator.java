package com.vaddya.hotelbooking.generator;

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

    public static Faker faker = new Faker(new Locale("en"));

    private static Calendar cal = Calendar.getInstance();

    private static Date checkoutUntil;
    private static Date checkinFrom;

    public static Date systemFrom;
    public static Date systemTo;

    static {
        cal.set(2017, Calendar.JANUARY, 1, 0, 0, 0);
        checkoutUntil = cal.getTime();
        checkinFrom = cal.getTime();
        systemFrom = cal.getTime();
        cal.set(2018, Calendar.DECEMBER, 31);
        systemTo = cal.getTime();
    }

    public static BonusPenalty bonusPenalty() {
        return new BonusPenalty(
                faker.lorem().sentence(10),
                RandomUtils.randomPrice()
        );
    }

    public static Cancellation cancellation() {
        return new Cancellation(
                RandomUtils.random(Constants.cancelStatuses)
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
                faker.weather().description()
        );
    }

    public static Guest guest() {
        return new Guest(
                faker.name().name(),
                RandomUtils.random.nextInt(5) == 0
        );
    }

    public static Hotel hotel(City city, HouseRules houseRules) {
        return new Hotel(
                faker.company().name(),
                city,
                houseRules,
                faker.address().streetAddress(),
                RandomUtils.randomShort(),
                faker.lorem().paragraph()
        );
    }

    public static HouseRules houseRules() {
        checkoutUntil.setHours(faker.random().nextInt(9, 12));
        checkinFrom.setHours(faker.random().nextInt(12, 15));
        return new HouseRules(
                DateUtils.toSqlTime(checkinFrom),
                DateUtils.toSqlTime(checkoutUntil),
                faker.lorem().sentence()
        );
    }

    public static Price initialPrice() {
        return price(systemFrom);
    }

    public static Price nextPrice(Price price) {
        Date date = price.getTo();
        cal.setTime(date);
        cal.add(Calendar.DAY_OF_MONTH, 1);
        return price(cal.getTime());
    }

    public static Price price(Date from) {
        Date randomTo = faker.date().future(365, TimeUnit.DAYS, from);
        return new Price(
                DateUtils.toSqlDate(from),
                DateUtils.toSqlDate(randomTo),
                RandomUtils.randomPrice()
        );
    }

    public static Reservation reservation(Room room, User user) {
        Date randomFrom = faker.date().between(systemFrom, systemTo);
        Date randomTo = faker.date().future(20, TimeUnit.DAYS, randomFrom);
        return new Reservation(
                room,
                user,
                DateUtils.toSqlDate(randomFrom),
                DateUtils.toSqlDate(randomTo),
                RandomUtils.randomPrice(),
                RandomUtils.random.nextInt(10) != 0
        );
    }

    public static Review review() {
        return new Review(
                faker.gameOfThrones().quote(),
                faker.hobbit().quote(),
                RandomUtils.randomShort()
        );
    }

    public static Room room(RoomType roomType) {
        return new Room(
                roomType,
                faker.book().title()
        );
    }

    public static RoomType roomType(Hotel hotel, Set<Facility> facilities) {
        Constants.RoomTypeInfo roomType = RandomUtils.random(Constants.roomTypes);
        return new RoomType(
                hotel,
                roomType.getType(),
                roomType.getCapacity(),
                roomType.getDescription(),
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
