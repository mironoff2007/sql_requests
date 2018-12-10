package com.example.postgresdemo.controller;

import com.example.postgresdemo.model.Shop;
import com.example.postgresdemo.repository.ShopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;

@RestController
public class ShopController {

    @Autowired
    private ShopRepository shopRepository;

    @PersistenceContext
    private EntityManager em;
    private Object name;

    @EventListener(ApplicationReadyEvent.class)
    public void doSomethingAfterStartup() {
        System.out.println("ShopController ready");
        addShops();
        doQuery();
        doInnerJoinQuery();
    }
    public void addShops() {
        ArrayList<Shop> customerList= new  ArrayList<>();
        customerList.add(new Shop((long) 1,"Shop1","City1",9));
        customerList.add(new Shop((long) 2,"Shop2","City2",4));
        customerList.add(new Shop((long) 3,"Shop3","City3",7));
        customerList.add(new Shop((long) 4,"Shop4","City1",8));
        customerList.add(new Shop((long) 5,"Shop5","City2",2));
        customerList.add(new Shop((long) 6,"Shop6","City3",5));
        customerList.forEach(v->shopRepository.save(v));


    }

    public void doQuery(){
        em.createNativeQuery("Select * from shops ",Shop.class).getResultList().forEach(v->  System.out.println(v));
    }
    public void doInnerJoinQuery()
    {
        em.createNativeQuery(" Select Distinct shops.name"
                            +" From shops,customers"
                            +" Where shops.city=customers.city").getResultList().forEach(v-> {name = v;System.out.println(name);});
    }
}
