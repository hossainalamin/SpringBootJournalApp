package com.hossainalamin.SpringBootProject.services;

import com.hossainalamin.SpringBootProject.api.response.WeatherResponse;
import com.hossainalamin.SpringBootProject.entity.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.stream.DoubleStream;

@Component
public class WeatherApiConsume {
    @Value("${weather.api.key}")
    public String apiKey;
    public static final String apiUrl = "http://api.weatherstack.com/current?access_key=API_KEY&query=CITY";
    @Autowired
    private RestTemplate restTemplate;
    public WeatherResponse getWeather(String city){
        String finalApiUrl = apiUrl.replace("API_KEY", apiKey).replace("CITY", city);
        ResponseEntity<WeatherResponse> response = restTemplate.exchange(finalApiUrl, HttpMethod.GET, null, WeatherResponse.class);//deserialize
        return response.getBody();
    }

    public WeatherResponse postWeather(String city){
        String finalApiUrl = apiUrl.replace("API_KEY", apiKey).replace("CITY", city);
        UserDetails user = User.builder().username("alamin1").password("alamin1").build();
        HttpEntity<UserDetails> responseEntity = new HttpEntity<>(user);
        ResponseEntity<WeatherResponse> response = restTemplate.exchange(finalApiUrl, HttpMethod.GET, responseEntity, WeatherResponse.class);//deserialize
        return response.getBody();
    }
}
