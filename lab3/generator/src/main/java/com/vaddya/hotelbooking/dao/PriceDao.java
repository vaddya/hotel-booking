package com.vaddya.hotelbooking.dao;

import com.vaddya.hotelbooking.model.Price;

public class PriceDao extends EntityDao<Price, Long> {

    @Override
    protected Class<Price> getEntityClass() {
        return Price.class;
    }
}
