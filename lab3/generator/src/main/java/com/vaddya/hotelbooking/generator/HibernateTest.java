package com.vaddya.hotelbooking.generator;

import com.vaddya.hotelbooking.dao.RoomTypeDao;
import com.vaddya.hotelbooking.model.RoomType;

public class HibernateTest {

    private static RoomTypeDao dao = new RoomTypeDao();

    public static void main(String[] args) {
        var session = HibernateSessionFactory.getSessionFactory().openSession();
        dao.setSession(session);
        RoomType rt = dao.find(1L);
        System.out.println(rt);
        HibernateSessionFactory.shutdown();
    }

}
