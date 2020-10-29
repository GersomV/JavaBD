package org.example.dao;

import javax.persistence.EntityManager;
import java.lang.reflect.ParameterizedType;
import java.util.List;

public class Dao<T> {

    protected final EntityManager em;

    public T get(long id) {
        return em.find(T(), id);
    }

    @SuppressWarnings("unchecked")
    public Dao(EntityManager em) {
        this.em = em;

    }

    private String typeSimple() {
        return T().getSimpleName();
    }

    @SuppressWarnings("unchecked")
    private Class<T> T() {
        return (Class<T>) ((ParameterizedType) getClass()
                .getGenericSuperclass()).getActualTypeArguments()[0];
    }

    public void save(T e) {
        em.getTransaction().begin();
        em.persist(e);
        em.getTransaction().commit();
    }

    public void saveAndDetach(T e) {
        em.getTransaction().begin();
        em.persist(e);
        detach();
        em.getTransaction().commit();
    }

    private void detach() {
        em.flush();
        em.clear();
    }

    public T update(T e) {
        em.getTransaction().begin();
        T merged = em.merge(e);
        em.getTransaction().commit();
        return merged;
    }

    public void remove(T e) {
        em.getTransaction().begin();
        em.remove(e);
        em.getTransaction().commit();
    }

    public boolean isManaged(T e) {
        return em.contains(e);
    }

    public List<T> findAll() {
        return em.createQuery("SELECT e FROM " + typeSimple() + " e ", T()).getResultList();
    }

    public List<T> findAllWithNamedQuery() {
        return em.createNamedQuery(typeSimple() + ".findAll", T()).getResultList();
    }

}