package com.vaddya.hotelbooking.dao;

import com.vaddya.hotelbooking.model.Hotel;

public class HotelDao extends EntityDao<Hotel, Integer> {

    @Override
    protected Class<Hotel> getEntityClass() {
        return Hotel.class;
    }
}
