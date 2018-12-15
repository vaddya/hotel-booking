package com.vaddya.hotelbooking.generator;

import java.util.HashSet;
import java.util.Random;
import java.util.logging.Level;

import org.apache.commons.cli.ParseException;
import org.apache.log4j.Logger;
import org.hibernate.Session;

import com.vaddya.hotelbooking.dao.BonusPenaltyDao;
import com.vaddya.hotelbooking.dao.CityDao;
import com.vaddya.hotelbooking.dao.CountryDao;
import com.vaddya.hotelbooking.dao.FacilityDao;
import com.vaddya.hotelbooking.dao.HotelDao;
import com.vaddya.hotelbooking.dao.HouseRulesDao;
import com.vaddya.hotelbooking.dao.ReservationDao;
import com.vaddya.hotelbooking.dao.RoomDao;
import com.vaddya.hotelbooking.dao.RoomTypeDao;
import com.vaddya.hotelbooking.dao.UserDao;
import com.vaddya.hotelbooking.model.Country;
import com.vaddya.hotelbooking.model.Facility;
import com.vaddya.hotelbooking.model.Price;

import static com.vaddya.hotelbooking.generator.RandomUtils.random;
import static com.vaddya.hotelbooking.generator.RandomUtils.randomByPredicate;

public class Generator {

    private static final BonusPenaltyDao bonusPenaltyDao = new BonusPenaltyDao();
    private static final CityDao cityDao = new CityDao();
    private static final CountryDao countryDao = new CountryDao();
    private static final FacilityDao facilityDao = new FacilityDao();
    private static final HotelDao hotelDao = new HotelDao();
    private static final HouseRulesDao houseRulesDao = new HouseRulesDao();
    private static final ReservationDao reservationDao = new ReservationDao();
    private static final RoomDao roomDao = new RoomDao();
    private static final RoomTypeDao roomTypeDao = new RoomTypeDao();
    private static final UserDao userDao = new UserDao();

    private static final Random random = new Random(System.currentTimeMillis());
    private static final Logger logger = Logger.getLogger(Generator.class);

    public static void main(String[] args) {
        java.util.logging.Logger.getLogger("org.hibernate").setLevel(Level.OFF);
        GeneratorOptions options;
        try {
            options = new GeneratorOptions(args);
            if (options.hasHelpOption() || args.length == 0) {
                options.printHelp();
                return;
            }
        } catch (ParseException e) {
            e.printStackTrace();
            return;
        }
        try (var session = HibernateSessionFactory.getSessionFactory().openSession()) {
            var cluster = options.getClusterOption();
            if (cluster.isPresent()) {
                var number = options.getClusterBaseNumber();
                switch (cluster.get()) {
                case CITY:
                    generateCountries(session, number / 20 + 1);
                    generateCities(session, number);
                    generateUsers(session, number * 10);
                    break;
                case HOTEL:
                    generateFacilities(session, 100);
                    generateHouseRules(session, number / 2 + 1);
                    generateHotels(session, number);
                    generateRoomTypes(session);
                    generateRooms(session, number * 50, 5, 15, true);
                    break;
                case RESERVATION:
                    generateBonusPenalties(session, 20);
                    generateReservations(session, number, 0, 3, true);
                    generateCancellations(session, number / 4 + 1);
                    generateReviews(session, number / 4 + 1);
                }
                return;
            }

            if (options.hasCountries() || options.hasAllOption()) {
                generateCountries(session, options.getCountries());
            }
            if (options.hasCities() || options.hasAllOption()) {
                generateCities(session, options.getCities());
            }
            if (options.hasFacilities() || options.hasAllOption()) {
                generateFacilities(session, options.getFacilities());
            }
            if (options.hasHouseRules() || options.hasAllOption()) {
                generateHouseRules(session, options.getHouseRules());
            }
            if (options.hasHotels() || options.hasAllOption()) {
                generateHotels(session, options.getHotels());
            }
            if (options.hasRoomTypes() || options.hasAllOption()) {
                generateRoomTypes(session);
            }
            if (options.hasRooms() || options.hasAllOption()) {
                generateRooms(session, options.getRooms(), options.getMinFacilities(), options.getMaxFacilities(), options.hasPrices());
            }
            if (options.hasUsers() || options.hasAllOption()) {
                generateUsers(session, options.getUsers());
            }
            if (options.hasBonusPenalties() || options.hasAllOption()) {
                generateBonusPenalties(session, options.getBonusPenalties());
            }
            if (options.hasReservations() || options.hasAllOption()) {
                generateReservations(session, options.getReservations(), options.getMinBonusPenalties(), options.getMaxBonusPenalties(), options.hasGuests());
            }
            if (options.hasCancellations() || options.hasAllOption()) {
                generateCancellations(session, options.getCancellation());
            }
            if (options.hasReviews() || options.hasAllOption()) {
                generateReviews(session, options.getReviews());
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HibernateSessionFactory.shutdown();
        }
    }

    private static void log(String format, Object... args) {
        logger.info(String.format(format, args));
    }

    private static void generateBonusPenalties(Session session, int count) {
        bonusPenaltyDao.setSession(session);
        for (int i = 0; i < count; i++) {
            if (i % 100 == 0) {
                log("generating bonus/penalties: %d/%d", i, count);
            }
            var bonusPenalty = EntityGenerator.bonusPenalty();
            bonusPenaltyDao.insert(bonusPenalty);
        }
        log("done with bonus/penalties");
    }

    private static void generateCancellations(Session session, int count) {
        reservationDao.setSession(session);
        var reservations = reservationDao.findAll();
        for (int i = 0; i < count; i++) {
            if (i % 100 == 0) {
                log("generating cancellations: %d/%d", i, count);
            }
            var cancellation = EntityGenerator.cancellation();
            var reservation = randomByPredicate(reservations, r -> r.getCancellation() == null);
            reservation.setCancellation(cancellation);
            reservationDao.insert(reservation);
        }
        log("done with cancellations");
    }

    private static void generateCities(Session session, int count) {
        var countries = countryDao.findAll();
        cityDao.setSession(session);
        for (int i = 0; i < count; i++) {
            if (i % 100 == 0) {
                log("generating cities: %d/%d", i, count);
            }
            Country country = random(countries);
            var city = EntityGenerator.city(country);
            cityDao.insert(city);
        }
        log("done with cities");
    }

    private static void generateCountries(Session session, int count) {
        countryDao.setSession(session);
        for (int i = 0; i < count; i++) {
            if (i % 100 == 0) {
                log("generating countries: %d/%d", i, count);
            }
            var country = EntityGenerator.country();
            countryDao.insert(country);
        }
        log("done with counties");
    }

    private static void generateFacilities(Session session, int count) {
        facilityDao.setSession(session);
        for (int i = 0; i < count; i++) {
            if (i % 100 == 0) {
                log("generating facilities: %d/%d", i, count);
            }
            var facility = EntityGenerator.facility();
            facilityDao.insert(facility);
        }
        log("done with facilities");
    }

    private static void generateHotels(Session session, int count) {
        hotelDao.setSession(session);
        cityDao.setSession(session);
        houseRulesDao.setSession(session);
        var cities = cityDao.findAll();
        var houseRules = houseRulesDao.findAll();
        for (int i = 0; i < count; i++) {
            if (i % 100 == 0) {
                log("generating hotels: %d/%d", i, count);
            }
            var city = random(cities);
            var houseRule = random(houseRules);
            var hotel = EntityGenerator.hotel(city, houseRule);
            hotelDao.insert(hotel);
        }
        log("done with hotels");
    }

    private static void generateHouseRules(Session session, int count) {
        houseRulesDao.setSession(session);
        for (int i = 0; i < count; i++) {
            if (i % 100 == 0) {
                log("generating house rules: %d/%d", i, count);
            }
            var houseRules = EntityGenerator.houseRules();
            houseRulesDao.insert(houseRules);
        }
        log("done with house rules");
    }

    private static void generateReservations(Session session, int count, int minBonusPenalties, int maxBonusPenalties, boolean genGuest) {
        reservationDao.setSession(session);
        roomDao.setSession(session);
        userDao.setSession(session);
        bonusPenaltyDao.setSession(session);
        var rooms = roomDao.findAll();
        var users = userDao.findAll();
        var bonusPenalties = bonusPenaltyDao.findAll();
        for (int i = 0; i < count; i++) {
            if (i % 100 == 0) {
                log("generating reservations: %d/%d", i, count);
            }
            var room = random(rooms);
            var user = random(users);
            var reservation = EntityGenerator.reservation(room, user);
            if (random.nextInt(5) == 0) {
                var numberOfBonusPenalties = minBonusPenalties +
                        random.nextInt(maxBonusPenalties - minBonusPenalties);
                for (int j = 0; j < numberOfBonusPenalties; j++) {
                    reservation.addBonusPenalty(random(bonusPenalties));
                }
            }
            if (genGuest) {
                for (int j = 0; j < random.nextInt(3) + 1; j++) {
                    var guest = EntityGenerator.guest();
                    reservation.addGuest(guest);
                }
            }
            reservationDao.insert(reservation);
        }
        log("done with reservations");
    }

    private static void generateReviews(Session session, int count) {
        reservationDao.setSession(session);
        var reservations = reservationDao.findAll();
        for (int i = 0; i < count; i++) {
            if (i % 100 == 0) {
                log("generating reviews: %d/%d", i, count);
            }
            var review = EntityGenerator.review();
            var reservation = randomByPredicate(reservations, r -> r.getReview() == null);
            reservation.setReview(review);
            reservationDao.insert(reservation);
        }
        log("done with reviews");
    }

    private static void generateRooms(Session session, int count, int minFacilities, int maxFacilities, boolean genPrices) {
        roomDao.setSession(session);
        hotelDao.setSession(session);
        roomTypeDao.setSession(session);
        facilityDao.setSession(session);
        var hotels = hotelDao.findAll();
        var roomTypes = roomTypeDao.findAll();
        var facilities = facilityDao.findAll();
        for (int i = 0; i < count; i++) {
            if (i % 100 == 0) {
                log("generating rooms: %d/%d", i, count);
            }
            var hotel = random(hotels);
            var roomType = random(roomTypes);
            var facility = new HashSet<Facility>();
            var numberOfFacilities = minFacilities + random.nextInt(maxFacilities - minFacilities);
            for (int j = 0; j < numberOfFacilities; j++) {
                facility.add(random(facilities));
            }
            var prices = new HashSet<Price>();
            if (genPrices) {
                var price = EntityGenerator.initialPrice();
                while (price.getTo().before(EntityGenerator.systemTo)) {
                    price = EntityGenerator.nextPrice(price);
                    prices.add(price);
                }
            }
            var room = EntityGenerator.room(hotel, roomType, facility, prices);
            roomDao.insert(room);
        }
        log("done with rooms");
    }

    private static void generateRoomTypes(Session session) {
        roomTypeDao.setSession(session);
        log("generating room types");
        var roomTypes = EntityGenerator.roomTypes();
        for (var roomType : roomTypes) {
            roomTypeDao.insert(roomType);
        }
        log("done with room types");
    }

    private static void generateUsers(Session session, int count) {
        userDao.setSession(session);
        cityDao.setSession(session);
        var cities = cityDao.findAll();
        for (int i = 0; i < count; i++) {
            if (i % 100 == 0) {
                log("generating users: %d/%d", i, count);
            }
            var city = random(cities);
            var user = EntityGenerator.user(city);
            userDao.insert(user);
        }
        log("done with users");
    }
}
