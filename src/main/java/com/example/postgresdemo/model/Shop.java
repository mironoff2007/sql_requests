package com.example.postgresdemo.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "shops")
public class Shop {
    @Id
    private Long id;

    @Column(columnDefinition = "name")
    private String name;

    @Column(columnDefinition = "text")
    private String city;

    @Column(columnDefinition = "int")
    private int rating;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public Shop(Long id,String name,String city,int rating) {
        this.id = id;
        this.name = name;
        this.city = city;
        this.rating = rating;
    }

    public Shop() {

    }
    
}
