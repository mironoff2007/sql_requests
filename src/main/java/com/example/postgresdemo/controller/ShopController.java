package com.example.postgresdemo.controller;

import com.example.postgresdemo.model.Shop;
import com.example.postgresdemo.repository.ShopRepository;
import com.example.postgresdemo.util.QueryPrinter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;

@RestController
public class ShopController {

    @Autowired
    private ShopRepository shopRepository;

    @PersistenceContext
    private EntityManager em;
    private Object name;

    private QueryPrinter printer;

    @EventListener(ApplicationReadyEvent.class)
    public void doSomethingAfterStartup() {
        printer = new QueryPrinter();
        System.out.println("ShopController ready");
        addShops();
        doQuery();
        doInnerJoinQuery();
        doLeftJoinQuery();
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
        System.out.println();
        printer.printResult("Select city from shops ",em);

    }
    public void doInnerJoinQuery()
    {
        System.out.println();
        System.out.println("Shops-Customers INNER JOIN");

        printer.printResult(" Select shops.shop_name,customers.name" +
                " From shops\n" +
                " INNER JOIN customers \n" +
                " ON shops.city=customers.city",em);

    }
    public void doLeftJoinQuery()
    {
        System.out.println();
        System.out.println("Shops-Customers LEFT JOIN");

        printer.printResult(" Select shops.shop_name,customers.name" +
                " From shops\n" +
                " Left JOIN customers \n" +
                " ON shops.city=customers.city",em);

    }

}
