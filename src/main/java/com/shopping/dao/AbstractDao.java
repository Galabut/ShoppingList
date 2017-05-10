package com.shopping.dao;

import javax.persistence.EntityManager;

public interface AbstractDao<T> {
    public void create(T entity, EntityManager em);

    public void delete(T entity, EntityManager em);

    public T update(T entity, EntityManager em);

    public T getById(String id, EntityManager em);

}
