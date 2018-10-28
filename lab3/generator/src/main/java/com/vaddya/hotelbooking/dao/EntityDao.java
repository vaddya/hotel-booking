package com.vaddya.hotelbooking.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Session;

public abstract class EntityDao<E, I extends Serializable> implements Dao<E, I> {

    private Session session;

    protected abstract Class<E> getEntityClass();

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }

    @Override
    public boolean insert(E entity) {
        session.beginTransaction();
        try {
            session.save(entity);
            session.getTransaction().commit();
            return true;
        } catch (Exception e) {
            System.out.println(e.toString());
            session.getTransaction().rollback();
            return false;
        }
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
    public boolean update(E entity) {
        session.beginTransaction();
        try {
            session.update(entity);
            session.getTransaction().commit();
            return true;
        } catch (Exception e) {
            System.out.println(e.toString());
            session.getTransaction().rollback();
            return false;
        }
    }

    @Override
    public void delete(I id) {
        session.beginTransaction();
        var entity = session.load(getEntityClass(), id);
        session.delete(entity);
        session.getTransaction().commit();
    }
}
