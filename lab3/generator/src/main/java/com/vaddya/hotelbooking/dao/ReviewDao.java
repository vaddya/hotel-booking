package com.vaddya.hotelbooking.dao;

import com.vaddya.hotelbooking.model.Review;

public class ReviewDao extends EntityDao<Review, Long> {

    @Override
    protected Class<Review> getEntityClass() {
        return Review.class;
    }
}
