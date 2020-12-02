package org.example.dao;

import javax.persistence.EntityManager;
import java.lang.reflect.ParameterizedType;
import java.util.List;

public abstract class Dao<E, I> {

    protected final EntityManager em;
    public Dao(EntityManager em) { this.em = em; }
    public E get(I id) {
        return em.find(E(), id);
    }

    public void save(E e) {
        em.getTransaction().begin();
        em.persist(e);
        em.getTransaction().commit();
    }

    public void saveAndDetach(E e) {
        em.getTransaction().begin();
        em.persist(e);
        em.flush();
        em.detach(e);
        em.getTransaction().commit();
    }

    public E update(E e) {
        em.getTransaction().begin();
        E merged = em.merge(e);
        em.getTransaction().commit();
        return merged;
    }

    public void remove(E e) {
        em.getTransaction().begin();
        em.remove(e);
        em.getTransaction().commit();
    }



    public List<E> findAll() {
        return em.createQuery("SELECT e FROM " + typeSimple() + " e ", E()).getResultList();
    }

    public List<E> findAllWithNamedQuery() {
        return em.createNamedQuery(typeSimple() + ".findAll", E()).getResultList();
    }

    private String typeSimple() { return E().getSimpleName(); }


    private Class<E> E() {
        ParameterizedType thisDaoClass = (ParameterizedType) getClass().getGenericSuperclass();
        return (Class<E>) thisDaoClass.getActualTypeArguments()[0];
    }

}
