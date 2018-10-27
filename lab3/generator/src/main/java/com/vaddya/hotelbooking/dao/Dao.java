package com.vaddya.hotelbooking.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

public interface Dao<E, I extends Serializable> {

    void insert(E t);

    E find(I id);

    List<E> findAll();

    void save(E t);

    void delete(I id);
}

