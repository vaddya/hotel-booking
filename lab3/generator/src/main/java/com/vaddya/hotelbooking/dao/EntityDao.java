package com.vaddya.hotelbooking.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Session;

public abstract class EntityDao<E, I extends Serializable> implements Dao<E, I> {

    protected Session session;

    protected abstract Class<E> getEntityClass();

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }

    @Override
    public void insert(E entity) {
        session.beginTransaction();
        session.save(entity);
        session.getTransaction().commit();
    }

    @Override
    public E find(I id) {
        return session.get(getEntityClass(), id);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<E> findAll() {
        return session.createCriteria(getEntityClass()).list();
    }

    @Override
    public void update(E entity) {
        session.beginTransaction();
        session.update(entity);
        session.getTransaction().commit();
    }

    @Override
    public void delete(I id) {
        session.beginTransaction();
        var entity = session.load(getEntityClass(), id);
        session.delete(entity);
        session.getTransaction().commit();
    }
}
