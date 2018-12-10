package com.example.postgresdemo.controller;

import com.example.postgresdemo.model.Customer;
import com.example.postgresdemo.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;

@RestController
public class CustomerController {

    @Autowired
    private CustomerRepository customerRepository;

    @PersistenceContext
    private EntityManager em;

    @EventListener(ApplicationReadyEvent.class)
    public void doSomethingAfterStartup() {
        System.out.println("CustomerController ready");
        addCustomer();
        doQuery();
    }
    public void addCustomer() {
        ArrayList<Customer> customerList= new  ArrayList<>();
        customerList.add(new Customer((long) 1,"Customer1","City1"));
        customerList.add(new Customer((long) 2,"Customer2","City2",2));
        customerList.add(new Customer((long) 3,"Customer3"));
        customerList.add(new Customer((long) 4,"Customer4","City1"));
        customerList.add(new Customer((long) 5,"Customer5","City2",2));
        customerList.add(new Customer((long) 6,"Customer6"));



        customerList.forEach(v->customerRepository.save(v));


    }

    public void doQuery(){
        System.out.println();
        em.createNativeQuery("Select * from customers ",Customer.class).getResultList().forEach(v->  System.out.println(v));
    }
}
