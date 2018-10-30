package com.vaddya.hotelbooking.generator;

import java.util.HashSet;
import java.util.List;
import java.util.Random;

import org.hibernate.Session;

import com.vaddya.hotelbooking.dao.BonusPenaltyDao;
import com.vaddya.hotelbooking.dao.CancellationDao;
import com.vaddya.hotelbooking.dao.CityDao;
import com.vaddya.hotelbooking.dao.CountryDao;
import com.vaddya.hotelbooking.dao.FacilityDao;
import com.vaddya.hotelbooking.dao.GuestDao;
import com.vaddya.hotelbooking.dao.HotelDao;
import com.vaddya.hotelbooking.dao.HouseRulesDao;
import com.vaddya.hotelbooking.dao.PriceDao;
import com.vaddya.hotelbooking.dao.ReservationDao;
import com.vaddya.hotelbooking.dao.ReviewDao;
import com.vaddya.hotelbooking.dao.RoomDao;
import com.vaddya.hotelbooking.dao.RoomTypeDao;
import com.vaddya.hotelbooking.dao.UserDao;
import com.vaddya.hotelbooking.model.Country;
import com.vaddya.hotelbooking.model.Facility;

public class Generator {

    private static final BonusPenaltyDao bonusPenaltyDao = new BonusPenaltyDao();
    private static final CancellationDao cancellationDao = new CancellationDao();
    private static final CityDao cityDao = new CityDao();
    private static final CountryDao countryDao = new CountryDao();
    private static final GuestDao guestDao = new GuestDao();
    private static final FacilityDao facilityDao = new FacilityDao();
    private static final HouseRulesDao houseRulesDao = new HouseRulesDao();
    private static final HotelDao hotelDao = new HotelDao();
    private static final PriceDao priceDao = new PriceDao();
    private static final ReservationDao reservationDao = new ReservationDao();
    private static final ReviewDao reviewDao = new ReviewDao();
    private static final RoomDao roomDao = new RoomDao();
    private static final RoomTypeDao roomTypeDao = new RoomTypeDao();
    private static final UserDao userDao = new UserDao();

    private static final Random random = new Random();

    public static void main(String[] args) {
        try (var session = HibernateSessionFactory.getSessionFactory().openSession()) {
            GeneratorOptions options = new GeneratorOptions(args);
            if (options.hasHelpOption()) {
                options.printHelp();
                return;
            }
            var cluster = options.getClusterOption();
            if (cluster.isPresent()) {
                var number = options.getClusterBaseNumber();
                switch (cluster.get()) {
                case CITY:
                    generateCountries(session, number / 20 + 1);
                    generateCities(session, number);
                    break;
                case HOTEL:
                    generateFacilities(session, 100);
                    generateHouseRules(session, number / 2 + 1);
                    generateHotels(session, number);
                    generateRoomTypes(session, number * 10, 5, 15);
                    generateRooms(session, number * 50);
                    generatePrices(session, number * 100);
                    break;
                case RESERVATION:
                    generateBonusPenalties(session, 20);
                    generateReservations(session, number, 0, 3);
                    generateGuests(session, number * 2);
                    generateCancellations(session, number / 4 + 1);
                    generateReviews(session, number / 4 + 1);
                }
                return;
            }
            generateCountries(session, options.getCountries());
            generateCities(session, options.getCities());
            generateFacilities(session, options.getFacilities());
            generateHouseRules(session, options.getHouseRules());
            generateHotels(session, options.getHotels());
            generateRoomTypes(session, options.getRoomTypes(), options.getMinFacilities(), options.getMaxFacilities());
            generateRooms(session, options.getRooms());
            generateUsers(session, options.getUsers());
            generateBonusPenalties(session, options.getBonusPenalties());
            generateReservations(session, options.getReservations(), options.getMinBonusPenalties(), options.getMaxBonusPenalties());
            generateGuests(session, options.getGuests());
            generateCancellations(session, options.getCancellation());
            generateReviews(session, options.getReviews());
        } catch (Exception e) {
            System.err.println(e.toString());
        } finally {
            HibernateSessionFactory.shutdown();
        }
    }

    private static <T> T random(List<T> list) {
        return list.get(random.nextInt(list.size()));
    }

    private static void log(String format, Object... args) {
        System.out.println(String.format(format, args));
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
        cancellationDao.setSession(session);
        reservationDao.setSession(session);
        var reservations = reservationDao.findAll();
        for (int i = 0; i < count; i++) {
            if (i % 100 == 0) {
                log("generating cancellations: %d/%d", i, count);
            }
            var cancellation = EntityGenerator.cancellation();
            var reservation = random(reservations);
            cancellation.setReservation(reservation);
            cancellationDao.insert(cancellation);
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

    private static void generateGuests(Session session, int count) {
        guestDao.setSession(session);
        reservationDao.setSession(session);
        var reservations = reservationDao.findAll();
        for (int i = 0; i < count; i++) {
            if (i % 100 == 0) {
                log("generating guests: %d/%d", i, count);
            }
            var guest = EntityGenerator.guest();
            var reservation = random(reservations);
            guest.setReservation(reservation);
            guestDao.insert(guest);
        }
        log("done with guests");
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

    private static void generatePrices(Session session, int count) {
        priceDao.setSession(session);
        roomTypeDao.setSession(session);
        var roomTypes = roomTypeDao.findAll();
        for (int i = 0; i < count; i++) {
            if (i % 100 == 0) {
                log("generating prices: %d/%d", i, count);
            }
            var price = EntityGenerator.price();
            var roomType = random(roomTypes);
            price.setRoomType(roomType);
            priceDao.insert(price);
        }
        log("done with prices");
    }

    private static void generateReservations(Session session, int count, int minBonusPenalties, int maxBonusPenalties) {
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
            var numberOfBonusPenalties = minBonusPenalties +
                    random.nextInt(maxBonusPenalties - minBonusPenalties);
            var reservation = EntityGenerator.reservation(room, user);
            for (int j = 0; j < numberOfBonusPenalties; j++) {
                reservation.addBonusPenalty(random(bonusPenalties));
            }
            reservationDao.insert(reservation);
        }
        log("done with reservations");
    }

    private static void generateReviews(Session session, int count) {
        reviewDao.setSession(session);
        reservationDao.setSession(session);
        var reservations = reservationDao.findAll();
        for (int i = 0; i < count; i++) {
            if (i % 100 == 0) {
                log("generating reviews: %d/%d", i, count);
            }
            var review = EntityGenerator.review();
            var reservation = random(reservations);
            review.setReservation(reservation);
            reviewDao.insert(review);
        }
        log("done with reviews");
    }

    private static void generateRooms(Session session, int count) {
        roomDao.setSession(session);
        roomTypeDao.setSession(session);
        var roomTypes = roomTypeDao.findAll();
        for (int i = 0; i < count; i++) {
            if (i % 100 == 0) {
                log("generating rooms: %d/%d", i, count);
            }
            var roomType = random(roomTypes);
            var room = EntityGenerator.room(roomType);
            roomDao.insert(room);
        }
        log("done with rooms");
    }

    private static void generateRoomTypes(Session session, int count, int minFacilities, int maxFacilities) {
        roomTypeDao.setSession(session);
        hotelDao.setSession(session);
        facilityDao.setSession(session);
        var hotels = hotelDao.findAll();
        var facilities = facilityDao.findAll();
        for (int i = 0; i < count; i++) {
            if (i % 100 == 0) {
                log("generating room types: %d/%d", i, count);
            }
            var hotel = random(hotels);
            var facility = new HashSet<Facility>();
            var numberOfFacilities = minFacilities + random.nextInt(maxFacilities - minFacilities);
            for (int j = 0; j < numberOfFacilities; j++) {
                facility.add(random(facilities));
            }
            var roomType = EntityGenerator.roomType(hotel, facility);
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
