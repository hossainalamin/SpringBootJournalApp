package com.hossainalamin.SpringBootProject.controller;

import com.hossainalamin.SpringBootProject.entity.Users;
import com.hossainalamin.SpringBootProject.services.UserService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;
    @GetMapping
    public ResponseEntity<?> getAllUsers(){
        List<Users> usersList = userService.getAllUsers();
        if(usersList != null && !usersList.isEmpty()){
            return new ResponseEntity<>(HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody Users users){
        try {
            if(users != null) {
                boolean userCreate = userService.createUsers(users);
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
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
    @PutMapping("/id/{userId}")
    public ResponseEntity<?> updateUserById(@PathVariable ObjectId userId, @RequestBody Users user){
        boolean userIfo = userService.updateUserById(userId, user);
        if(userIfo){
            return new ResponseEntity<>(HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }
    @PutMapping
    public ResponseEntity<?> updateUserByUserName(@RequestBody Users users){
        Users userByUserName = userService.findUserByUserName(users.getUserName());
        if(userByUserName != null){
            userByUserName.setUserName(users.getUserName());
            userByUserName.setPassword(users.getPassword());
            userService.createUsers(userByUserName);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
