package com.vaddya.hotelbooking.dao;

import com.vaddya.hotelbooking.model.Facility;

public class FacilityDao extends DefaultDao<Facility, Integer> {

    protected Class<Facility> getEntityClass() {
        return Facility.class;
    }
}
