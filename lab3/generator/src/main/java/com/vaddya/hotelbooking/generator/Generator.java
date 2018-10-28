package com.vaddya.hotelbooking.generator;

import java.util.Random;

import org.hibernate.Session;

import com.vaddya.hotelbooking.dao.CityDao;
import com.vaddya.hotelbooking.dao.CountryDao;
import com.vaddya.hotelbooking.model.Country;

public class Generator {

    private static CountryDao countryDao = new CountryDao();
    private static CityDao cityDao = new CityDao();

    private static Random random = new Random();

    public static void main(String[] args) {
        var session = HibernateSessionFactory.getSessionFactory().openSession();

        var countriesCount = 50; // TODO: from args
        generateCountries(session, countriesCount);

        var citiesCount = 500;
        generateCities(session, citiesCount);

        HibernateSessionFactory.shutdown();
    }

    public static void generateCountries(Session session, int count) {
        countryDao.setSession(session);
        for (int i = 0; i < count; i++) {
            var country = EntityGenerator.country();
            countryDao.insert(country);
        }
    }

    public static void generateCities(Session session, int count) {
        var countries = countryDao.findAll();
        cityDao.setSession(session);
        for (int i = 0; i < count; i++) {
            Country country = countries.get(random.nextInt(countries.size()));
            var city = EntityGenerator.city(country);
            cityDao.insert(city);
        }
    }

}
