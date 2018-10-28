package com.vaddya.hotelbooking.dao;

import com.vaddya.hotelbooking.model.Country;

public class CountryDao extends EntityDao<Country, Integer> {

    @Override
    protected Class<Country> getEntityClass() {
        return Country.class;
    }
}
