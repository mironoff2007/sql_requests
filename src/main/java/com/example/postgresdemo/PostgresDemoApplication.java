package com.example.postgresdemo;

import com.example.postgresdemo.controller.CustomerController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;



@SpringBootApplication
@EnableJpaAuditing
public class PostgresDemoApplication {
    @Autowired CustomerController customerController;
	public static void main(String[] args) {
		SpringApplication.run(PostgresDemoApplication.class, args);


	}


}
