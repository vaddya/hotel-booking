package com.vaddya.hotelbooking.dao;

import com.vaddya.hotelbooking.model.Reservation;

public class ReservationDao extends EntityDao<Reservation, Long> {

    @Override
    protected Class<Reservation> getEntityClass() {
        return Reservation.class;
    }
}
