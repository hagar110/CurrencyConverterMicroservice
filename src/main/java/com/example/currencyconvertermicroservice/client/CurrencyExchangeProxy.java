package com.example.currencyconvertermicroservice.client;

import com.example.currencyconvertermicroservice.entity.CurrencyConversion;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Component
//@FeignClient(name="currency-exchange",url="localhost:8000")//microservice application name in .properties file
@FeignClient(name="currency-exchange")//url removed to enable load balancing
public interface CurrencyExchangeProxy {

    @GetMapping("/currency-exchange/from/{from}/to/{to}")
     CurrencyConversion retrieveExchangeValue(//targets the method in the controller of currency exchange microservice
            @PathVariable String from,
            @PathVariable String to);
}
