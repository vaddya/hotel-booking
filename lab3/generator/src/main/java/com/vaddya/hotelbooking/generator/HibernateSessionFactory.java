package com.vaddya.hotelbooking.generator;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

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

public class HibernateSessionFactory {

    private static StandardServiceRegistry registry;
    private static SessionFactory sessionFactory;

    private HibernateSessionFactory() {
    }

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Configuration configuration = new Configuration().configure();
                configuration.addAnnotatedClass(BonusPenalty.class);
                configuration.addAnnotatedClass(Cancellation.class);
                configuration.addAnnotatedClass(City.class);
                configuration.addAnnotatedClass(Country.class);
                configuration.addAnnotatedClass(Facility.class);
                configuration.addAnnotatedClass(Guest.class);
                configuration.addAnnotatedClass(Hotel.class);
                configuration.addAnnotatedClass(HouseRules.class);
                configuration.addAnnotatedClass(Price.class);
                configuration.addAnnotatedClass(Reservation.class);
                configuration.addAnnotatedClass(Review.class);
                configuration.addAnnotatedClass(Room.class);
                configuration.addAnnotatedClass(RoomType.class);
                configuration.addAnnotatedClass(User.class);
                registry = new StandardServiceRegistryBuilder()
                        .applySettings(configuration.getProperties())
                        .build();
                sessionFactory = configuration.buildSessionFactory(registry);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return sessionFactory;
    }

    public static void shutdown() {
        if (registry != null) {
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }
}
