package com.vaddya.hotelbooking.dao;

import com.vaddya.hotelbooking.model.RoomType;

public class RoomTypeDao extends EntityDao<RoomType, Long> {

    @Override
    protected Class<RoomType> getEntityClass() {
        return RoomType.class;
    }
}
