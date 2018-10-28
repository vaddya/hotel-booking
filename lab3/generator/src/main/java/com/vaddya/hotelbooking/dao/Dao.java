package com.vaddya.hotelbooking.dao;

import java.io.Serializable;
import java.util.List;

public interface Dao<E, I extends Serializable> {

    boolean insert(E t);

    E find(I id);

    List<E> findAll();

    boolean update(E t);

    void delete(I id);
}

