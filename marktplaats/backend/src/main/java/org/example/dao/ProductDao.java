package org.example.dao;

import org.example.dao.Dao;

import org.example.domain.Product;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class ProductDao extends Dao<Product, Long> {
    public ProductDao(EntityManager em) {
        super(em);
    }

    public List<Product> findAllProducts() {
        TypedQuery<Product> query = em.createQuery("SELECT prod FROM Product prod ", Product.class);
        return query.getResultList();
    }

}