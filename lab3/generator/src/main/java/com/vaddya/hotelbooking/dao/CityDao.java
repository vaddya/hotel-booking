package com.vaddya.hotelbooking.dao;

import com.vaddya.hotelbooking.model.City;

public class CityDao extends EntityDao<City, Integer> {

    @Override
    protected Class<City> getEntityClass() {
        return City.class;
    }
}
