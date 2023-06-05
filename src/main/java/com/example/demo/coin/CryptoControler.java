package com.example.demo.coin;

import com.example.demo.CryptoRepository;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;


@RestController
public class CryptoControler {

    private final CryptoRepository cryptoRepository;

    public CryptoControler(CryptoRepository cryptoRepository) {
        this.cryptoRepository = cryptoRepository;
    }

    @PostMapping("/api/crypto/save")
    public Crypto saveCrypto(@RequestBody Crypto crypto) {
        return cryptoRepository.save(crypto);
    }



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


            JSONObject resultObject = new JSONObject();
            resultObject.put("name", name);
            resultObject.put("symbol", symbol);
            resultObject.put("price", price);
            resultObject.put("image",image);


            resultArray.put(resultObject);
        }

        return resultArray.toString();
    }



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
            String symbol = cryptoObject.getString("symbol");

            JSONObject resultObject = new JSONObject();
            resultObject.put("name", name);
            resultObject.put("symbol",symbol);
            resultArray.put(resultObject);
        }


        return resultArray.toString();
    }

    @GetMapping("/api/price")
    public String getPrice(){
        String url = "https://api.coingecko.com/api/v3/coins/markets?vs_currency=USD&order=market_cap_desc&per_page=100&page=1&sparkline=false&locale=en";
        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);

        String apiResponse = response.getBody();

        JSONArray cryptoArray = new JSONArray(apiResponse);
        JSONArray resultArray = new JSONArray();

        for (int i = 0; i < cryptoArray.length(); i++) {
            JSONObject cryptoObject = cryptoArray.getJSONObject(i);

            String name = cryptoObject.getString("name");
            String price = cryptoObject.getString("price");

            JSONObject resultObject = new JSONObject();
            resultObject.put("name", name);
            resultObject.put("price",price);
            resultArray.put(resultObject);
        }
        return  resultArray.toString();
    }

    @GetMapping("/api/{coin}/{currency}/price")
    public ResponseEntity<Object> getPrice(@PathVariable String coin, @PathVariable String currency){
        String apiUrl = "https://api.coingecko.com/api/v3/simple/price?ids=" + coin + "&vs_currencies=" + currency;

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Object> response = restTemplate.getForEntity(apiUrl, Object.class);

        return response;
    }






}
