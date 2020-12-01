package org.example.dao;

import org.example.dao.Dao;

import org.example.domain.Product;

import javax.persistence.EntityManager;

public class ProductDao extends Dao<Product, Long> {
    public ProductDao(EntityManager em) {
        super(em);
    }
}