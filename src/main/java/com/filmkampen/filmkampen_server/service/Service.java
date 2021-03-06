package com.filmkampen.filmkampen_server.service;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.persistence.EntityManager;

import com.filmkampen.filmkampen_server.entity.BaseEntity;
import com.filmkampen.filmkampen_server.manager.PersistenceManager;

public abstract class Service<T> {

    protected static EntityManager em;
    private Class<T> type;

    public Service() {
        em = PersistenceManager.instance().getFactory().createEntityManager();
        this.type = (Class<T>)
                ((ParameterizedType)getClass()
                .getGenericSuperclass())
                .getActualTypeArguments()[0];
    }

    public BaseEntity save(BaseEntity entity) {
        em.getTransaction().begin();
        if (null == entity.getId()) {
            em.persist(entity);
        } else {
            em.merge(entity);
        }
        em.getTransaction().commit();
        return entity;
    }

    public void remove(String id) {
        T emp = find(id);
        if (emp != null) {
            em.remove(emp);
        }
    }

    public T find(String id) {
        return em.find(this.type, id);
    }
    
    public List<T> list() {
        em.getTransaction().begin();
        List<T> entities = em.createQuery("select b from " + type.getSimpleName() + " b").getResultList();
        em.getTransaction().commit();
        return entities;
    }

}
