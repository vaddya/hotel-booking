package com.vaddya.hotelbooking.dao;

import com.vaddya.hotelbooking.model.HouseRules;

public class HouseRulesDao extends EntityDao<HouseRules, Integer> {

    @Override
    protected Class<HouseRules> getEntityClass() {
        return HouseRules.class;
    }
}
