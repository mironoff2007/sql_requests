package com.example.postgresdemo.util;

import org.springframework.util.StringUtils;
import javax.persistence.EntityManager;
import java.util.List;


public class QueryPrinter {
    //private JpaRepository<Customer, Long> repository;

    public  void printResult(String query,EntityManager em)
    {
        System.out.println("-\n"+query+"\n-");
        StringBuilder strB=new StringBuilder(query.toLowerCase());

        strB=new StringBuilder(strB.substring(0,strB.indexOf("from")));
        int n = StringUtils.countOccurrencesOf(strB.toString(), ",")+1;
       if(n==1){
           em.createNativeQuery(query).getResultList().forEach(v->  System.out.println(v));
       }
       else{
           List<Object[]> listQuery=em.createNativeQuery(query).getResultList();

           for (Object[] obj : listQuery) {
               for(int i=0;i<n;i++) {
                   if(obj[i]!=null) {
                       System.out.print(obj[i]);
                   }
                   else{
                       System.out.print("null");
                   }
                   System.out.print(" | ");
               }
               System.out.println();
           }
       }

    }

}
