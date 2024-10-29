package com.hossainalamin.SpringBootProject.services;

import com.hossainalamin.SpringBootProject.api.response.WeatherResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class WeatherApiConsume {
    public static final String apiKey = "2a198a8e06452586f142d584b819b1fd";
    public static final String apiUrl = "http://api.weatherstack.com/current?access_key=API_KEY&query=CITY";
    @Autowired
    private RestTemplate restTemplate;
    public WeatherResponse getWeather(String city){
        String finalApiUrl = apiUrl.replace("API_KEY", apiKey).replace("CITY", city);
        ResponseEntity<WeatherResponse> response = restTemplate.exchange(finalApiUrl, HttpMethod.GET, null, WeatherResponse.class);//deserialize
        return response.getBody();
    }
}
