package com.hossainalamin.SpringBootProject.controller;

import com.hossainalamin.SpringBootProject.api.response.WeatherResponse;
import com.hossainalamin.SpringBootProject.entity.Users;
import com.hossainalamin.SpringBootProject.services.UserService;
import com.hossainalamin.SpringBootProject.services.WeatherApiConsume;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.http.SecurityHeaders;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private WeatherApiConsume weatherApiConsume;
    @PutMapping
    public ResponseEntity<?> updateUserByUserName(@RequestBody Users users){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String name = authentication.getName();
        Users userByUserName = userService.findUserByUserName(name);
        userByUserName.setUserName(users.getUserName());
        userByUserName.setPassword(users.getPassword());
        userService.createNewUser(userByUserName);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @DeleteMapping
    public ResponseEntity<?> deleteUserByUserName(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String name = authentication.getName();
        boolean deleteUserByName = userService.deleteUserByName(name);
        if(deleteUserByName){
            return new ResponseEntity<>(HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
        }
    }

    @GetMapping("/get-user/{city}")
    public ResponseEntity<?> getIndividualUser(@PathVariable String city){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String name = authentication.getName();
        WeatherResponse weather = weatherApiConsume.getWeather(city);
        return new ResponseEntity<>("Hello "+name+ " Weather report time " +weather.getCurrent().getObservationTime() + "Temperature +" +weather.getCurrent().getTemperature(), HttpStatus.OK);
    }
}
