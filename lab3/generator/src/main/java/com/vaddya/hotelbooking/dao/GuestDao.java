package com.vaddya.hotelbooking.dao;

import com.vaddya.hotelbooking.model.Guest;

public class GuestDao extends EntityDao<Guest, Long> {

    @Override
    protected Class<Guest> getEntityClass() {
        return Guest.class;
    }
}
