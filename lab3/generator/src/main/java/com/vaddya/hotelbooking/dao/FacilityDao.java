package com.vaddya.hotelbooking.dao;

import com.vaddya.hotelbooking.model.Facility;

public class FacilityDao extends EntityDao<Facility, Integer> {

    protected Class<Facility> getEntityClass() {
        return Facility.class;
    }
}
