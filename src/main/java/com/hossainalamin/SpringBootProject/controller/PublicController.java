package com.hossainalamin.SpringBootProject.controller;

import com.hossainalamin.SpringBootProject.entity.Users;
import com.hossainalamin.SpringBootProject.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/public")
public class PublicController {
    @Autowired
    private UserService userService;
    @PostMapping("/create-user")
    public ResponseEntity<?> createUser(@RequestBody Users users){
        try {
            if(users != null) {
                boolean userCreate = userService.createAdminUsers(users);
                if(userCreate){
                    return new ResponseEntity<>(HttpStatus.CREATED);
                }else{
                    return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
                }
            }else{
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }
        catch (Exception ex){
            ex.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

}
