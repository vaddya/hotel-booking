package com.vaddya.hotelbooking.generator;

import com.vaddya.hotelbooking.dao.FacilityDao;
import com.vaddya.hotelbooking.model.Facility;

public class HibernateTest {

    private static FacilityDao dao = new FacilityDao();

    public static void main(String[] args) {
        var session = HibernateSessionFactory.getSessionFactory().openSession();
        dao.setSession(session);
        for (Facility f : dao.findAll()) {
            System.out.println(f);
        }
        HibernateSessionFactory.shutdown();
    }

}
