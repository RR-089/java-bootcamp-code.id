package com.codeid.be_eshopay;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class BeEshopayApplication {

    public static void main(String[] args) {
        SpringApplication.run(BeEshopayApplication.class, args);
    }

}
