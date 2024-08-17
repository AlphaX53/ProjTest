package com.example.stockquoteservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class StockQuoteServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(StockQuoteServiceApplication.class, args);
    }
}