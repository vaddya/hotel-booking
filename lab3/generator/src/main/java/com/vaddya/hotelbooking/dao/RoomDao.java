package com.vaddya.hotelbooking.dao;

import com.vaddya.hotelbooking.model.Room;

public class RoomDao extends EntityDao<Room, Long> {

    @Override
    protected Class<Room> getEntityClass() {
        return Room.class;
    }
}
