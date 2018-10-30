package com.vaddya.hotelbooking.dao;

import com.vaddya.hotelbooking.model.BonusPenalty;

public class BonusPenaltyDao extends EntityDao<BonusPenalty, Integer> {

    @Override
    protected Class<BonusPenalty> getEntityClass() {
        return BonusPenalty.class;
    }
}
