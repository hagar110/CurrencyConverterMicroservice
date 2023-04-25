package com.example.currencyconvertermicroservice.client;

import com.example.currencyconvertermicroservice.entity.CurrencyConversion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
@Component
public class CurrencyExchangeClient {
    private String url="http://localhost:8001/currency-exchange/from/%s/to/%s";
    private RestTemplate restTemplate;
    @Autowired
    public CurrencyExchangeClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public CurrencyConversion getExchangeValue(String from, String to){
        System.out.println(String.format(url,from,to));
        ResponseEntity<CurrencyConversion> response= restTemplate.getForEntity(String.format(url,from,to), CurrencyConversion.class);
        if(!response.getStatusCode().is2xxSuccessful()){
            System.out.printf("error "+response.getStatusCode().toString());
        }
        return  response.getBody();
    }
}
