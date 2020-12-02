package org.example.domain;



import javax.persistence.*;

import java.util.*;

import static javax.persistence.CascadeType.*;
import static javax.persistence.FetchType.EAGER;
import static javax.persistence.FetchType.LAZY;
import static javax.persistence.TemporalType.DATE;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Product extends AbstractEntity<Long> {

    private String title;
    private String body;
    private int price;

    @ManyToOne
    private User sellerRole;

    @ManyToOne
    private User buyerRole;



    public Product() { }

    public Product(String title, String body, int price) {
        this.title = title;
        this.body = body;
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }


    public User getUser() {
        return sellerRole;
    }

    public void setUser(User user) {
        this.sellerRole = user;
    }

    @Override
    public String toString() {
        return "Product{" +
                "title='" + title + '\'' +
                ", body='" + body + '\'' +
                ", price=" + price +
                ", seller=" + sellerRole +
                '}';
    }

}
