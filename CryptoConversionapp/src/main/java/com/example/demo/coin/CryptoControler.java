package com.example.demo.coin;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;


@RestController
public class CryptoControler {

    @GetMapping("/list")
    public String getCryptoList() {
        String url = "https://api.coingecko.com/api/v3/coins/markets?vs_currency=USD&order=market_cap_desc&per_page=100&page=1&sparkline=false&locale=en";

        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);

        String apiResponse = response.getBody();

        JSONArray cryptoArray = new JSONArray(apiResponse);
        JSONArray resultArray = new JSONArray();

        for (int i = 0; i < cryptoArray.length(); i++) {
            JSONObject cryptoObject = cryptoArray.getJSONObject(i);

            String name = cryptoObject.getString("name");
            String symbol = cryptoObject.getString("symbol");
            double price = cryptoObject.getDouble("current_price");
            String image = cryptoObject.getString("image");
            // Dodaj inne interesujące Cię pola

            JSONObject resultObject = new JSONObject();
            resultObject.put("name", name);
            resultObject.put("symbol", symbol);
            resultObject.put("price", price);
            resultObject.put("image",image);
            // Dodaj inne interesujące Cię pola do resultObject

            resultArray.put(resultObject);
        }

        return resultArray.toString();
    }

  /*  @GetMapping(path = "/{CryptoID}")
    public String getCrypto(@PathVariable("CryptoID") String CryptoID){
       String url = "https://api.coingecko.com/api/v3/coins/" + CryptoID;

       RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);

        String apiResponse = response.getBody();

        JSONArray cryptoArray = new JSONArray(apiResponse);
        JSONArray resultArray = new JSONArray();

        for (int i = 0; i < cryptoArray.length(); i++) {
            JSONObject cryptoObject = cryptoArray.getJSONObject(i);

            String name = cryptoObject.getString("name");
            String symbol = cryptoObject.getString("symbol");
            double price = cryptoObject.getDouble("current_price");
            String image = cryptoObject.getString("image");
            // Dodaj inne interesujące Cię pola

            JSONObject resultObject = new JSONObject();
            resultObject.put("name", name);
            resultObject.put("symbol", symbol);
            resultObject.put("price", price);
            resultObject.put("image",image);
            // Dodaj inne interesujące Cię pola do resultObject

            resultArray.put(resultObject);
        }

        return resultArray.toString();
    }*/

    @GetMapping("/api/data")
    public String getData() {
        String url = "https://api.coingecko.com/api/v3/coins/markets?vs_currency=USD&order=market_cap_desc&per_page=100&page=1&sparkline=false&locale=en";

        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);

        String apiResponse = response.getBody();

        JSONArray cryptoArray = new JSONArray(apiResponse);
        JSONArray resultArray = new JSONArray();

        for (int i = 0; i < cryptoArray.length(); i++) {
            JSONObject cryptoObject = cryptoArray.getJSONObject(i);

            String name = cryptoObject.getString("name");

            JSONObject resultObject = new JSONObject();
            resultObject.put("name", name);
            resultArray.put(resultObject);
        }


        return resultArray.toString();
    }



}
