package org.example.domain;

import javax.persistence.*;
import java.util.*;

import static javax.persistence.FetchType.EAGER;
import static javax.persistence.FetchType.LAZY;
import static javax.persistence.TemporalType.DATE;
import static javax.persistence.CascadeType.*;

@Entity

@Inheritance(strategy = InheritanceType.JOINED)
public class User extends AbstractEntity<Long> {

    private String userName;
    private String passWord;

    @OneToMany(mappedBy = "sellerRole", cascade = {PERSIST, MERGE, REMOVE})
    private List<Product> adList = new LinkedList<>();

    @OneToMany(mappedBy = "buyerRole", cascade = {PERSIST, MERGE, REMOVE})
    private List<Product> shoppingCard = new LinkedList<>();


    public User() { }

    public User(String userName, String passWord) {
        this.userName = userName;
        this.passWord = passWord;
    }


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public List<Product> getAdList() {
        return adList;
    }

    public void setAdList(List<Product> adList) {
        this.adList = adList;
    }

    public List<Product> getShoppingCard() {
        return shoppingCard;
    }

    public void setShoppingCard(List<Product> shoppingCard) {
        this.shoppingCard = shoppingCard;
    }

    public void addNewProductToCart(Product p) {
        this.shoppingCard.add(p);
    }

    public void addNewAd(Product p){this.adList.add(p);}
}