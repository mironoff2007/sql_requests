package com.example.postgresdemo.query;

import com.example.postgresdemo.model.Customer;
import com.example.postgresdemo.repository.CustomerRepository;
import com.example.postgresdemo.util.QueryPrinter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;

@Component
public class CustomerQuery {

    @Autowired
    private CustomerRepository customerRepository;

    @PersistenceContext
    private EntityManager em;

    private QueryPrinter printer;

    @EventListener(ApplicationReadyEvent.class)
    public void doSomethingAfterStartup() {
        System.out.println("CustomerController ready");
        printer = new QueryPrinter();
        addCustomer();
        doQuery();
        doQueryCustomersWOShops();
        doQueryAllCities();
    }
    public void addCustomer() {
        ArrayList<Customer> customerList= new  ArrayList<>();
        customerList.add(new Customer((long) 1,"Customer1","City1"));
        customerList.add(new Customer((long) 2,"Customer2","City2",2));
        customerList.add(new Customer((long) 3,"Customer3"));
        customerList.add(new Customer((long) 4,"Customer4","City1"));
        customerList.add(new Customer((long) 5,"Customer5","City2",2));
        customerList.add(new Customer((long) 6,"Customer6"));
        customerList.add(new Customer((long) 7,"Customer7","City4",2));
        customerList.add(new Customer((long) 8,"Customer8","City5",2));
        customerList.add(new Customer((long) 9,"Customer9","City11",2));
        customerList.add(new Customer((long) 10,"Customer10","City12",2));

        customerList.forEach(v->customerRepository.save(v));
    }

    public void doQuery(){
        System.out.println();
        em.createNativeQuery("Select * from customers ",Customer.class).getResultList().forEach(v->  System.out.println(v));
        }

    public void doQueryCustomersWOShops() {
        System.out.println();
        System.out.println("Customers without shops");

        printer.printResult(" Select C.name" +
                            " From customers C\n" +
                            " Left Join shops S\n" +
                            " ON C.city=S.city\n" +
                            " Where C.city IS NUll\n" +
                            " Order By C.name",em);
    }

    public void doQueryAllCities() {
        System.out.println();
        System.out.println("All cities");

        printer.printResult(" Select city From Customers\n" +
                            " Union\n" +
                            " Select city From Shops\n" +
                            " Order By city",em);
    }

}
