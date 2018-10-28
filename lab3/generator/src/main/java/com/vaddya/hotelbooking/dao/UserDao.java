package com.vaddya.hotelbooking.dao;

import com.vaddya.hotelbooking.model.User;

public class UserDao extends EntityDao<User, Integer> {

    @Override
    protected Class<User> getEntityClass() {
        return User.class;
    }
}
