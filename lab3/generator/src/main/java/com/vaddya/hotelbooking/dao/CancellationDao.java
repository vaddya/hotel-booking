package com.vaddya.hotelbooking.dao;

import com.vaddya.hotelbooking.model.Cancellation;

public class CancellationDao extends EntityDao<Cancellation, Long> {

    @Override
    protected Class<Cancellation> getEntityClass() {
        return Cancellation.class;
    }
}
