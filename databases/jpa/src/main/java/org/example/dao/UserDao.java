package org.example.dao;

import org.example.domain.*;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class UserDao extends Dao<User, Long> {

    public UserDao(EntityManager em) {
        super(em);
    }

    public User findAllAds(long id) {
        User u = em.find(User.class, id);
        List<Product> adList = u.getAdList();
        return u;
    }

    public User listShoppingCard(long id){
        User u = em.find(User.class, id);
        List <Product> shoppingCard = u.getShoppingCard();
        return u;
    }

    public void addProductToShoppingCart(User u, Product p) {
        u.addNewProductToCart(p);
        update(u);
    }

    public User currentUser(String loginName) {
        TypedQuery<User> query = em.createQuery("SELECT u FROM User u WHERE UPPER (u.userName) = :name", User.class);
        query.setParameter("name", loginName.toUpperCase());
        return query.getSingleResult();
    }

    public void placeANewAd(User u, Product p){
        u.addNewAd(p);
    }
}

