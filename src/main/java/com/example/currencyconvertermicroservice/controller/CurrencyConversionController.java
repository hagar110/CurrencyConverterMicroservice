package com.example.currencyconvertermicroservice.controller;

import com.example.currencyconvertermicroservice.client.CurrencyExchangeClient;
import com.example.currencyconvertermicroservice.client.CurrencyExchangeProxy;
import com.example.currencyconvertermicroservice.entity.CurrencyConversion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
public class CurrencyConversionController {
    private final CurrencyExchangeClient currencyExchangeClient;
    @Autowired
    private  CurrencyExchangeProxy currencyExchangeProxy;
    public CurrencyConversionController(CurrencyExchangeClient currencyExchangeClient) {
        this.currencyExchangeClient = currencyExchangeClient;
    }

    @GetMapping("/currency-conversion/from/{from}/to/{to}/quantity/{quantity}")
    public CurrencyConversion calculateCurrencyConversion(@PathVariable String from, @PathVariable String to, @PathVariable BigDecimal quantity){
       CurrencyConversion currencyConversion=currencyExchangeClient.getExchangeValue(from, to);// when working with docker this method will return 500 because it calls localhost:8000 which is no longer valid ,
        //to be able to work properly change localhost to currency-exchange
        return new CurrencyConversion(currencyConversion.getId(),from,to,quantity,currencyConversion.getConversionMultiple(),quantity.multiply(currencyConversion.getConversionMultiple()), currencyConversion.getEnvironment());
    }
    @GetMapping("/currency-conversion-feign/from/{from}/to/{to}/quantity/{quantity}")
    public CurrencyConversion calculateCurrencyConversionFeign(
            @PathVariable String from,
            @PathVariable String to,
            @PathVariable BigDecimal quantity
    ) {

        CurrencyConversion currencyConversion = currencyExchangeProxy.retrieveExchangeValue(from, to);

        return new CurrencyConversion(currencyConversion.getId(),
                from, to, quantity,
                currencyConversion.getConversionMultiple(),
                quantity.multiply(currencyConversion.getConversionMultiple()),
                currencyConversion.getEnvironment());

    }
}
