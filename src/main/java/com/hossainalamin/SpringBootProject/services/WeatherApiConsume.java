package com.hossainalamin.SpringBootProject.services;

import com.hossainalamin.SpringBootProject.api.response.WeatherResponse;
import com.hossainalamin.SpringBootProject.cache.JournalAppCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class WeatherApiConsume {
    @Autowired
    JournalAppCache journalAppCache;
    @Value("${weather.api.key}")
    public String apiKey;
    @Autowired
    private RestTemplate restTemplate;
    @Cacheable(value = "weather", key = "#city")
    public WeatherResponse getWeather(String city){
        String finalApiUrl = journalAppCache.APP_CACHE.get("weather_api").replace("<apiKey>", apiKey).replace("<city>", city);
        ResponseEntity<WeatherResponse> response = restTemplate.exchange(finalApiUrl, HttpMethod.GET, null, WeatherResponse.class);//deserialize
        return response.getBody();
    }

    public WeatherResponse postWeather(String city){
        String finalApiUrl = journalAppCache.APP_CACHE.get("weather_api").replace("API_KEY", apiKey).replace("CITY", city);
        UserDetails user = User.builder().username("alamin1").password("alamin1").build();
        HttpEntity<UserDetails> responseEntity = new HttpEntity<>(user);
        ResponseEntity<WeatherResponse> response = restTemplate.exchange(finalApiUrl, HttpMethod.GET, responseEntity, WeatherResponse.class);//deserialize
        return response.getBody();
    }
}
