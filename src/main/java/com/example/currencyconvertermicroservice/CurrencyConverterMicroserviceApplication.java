package com.example.currencyconvertermicroservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class CurrencyConverterMicroserviceApplication {

    public static void main(String[] args) {
        SpringApplication.run(CurrencyConverterMicroserviceApplication.class, args);
    }

}
