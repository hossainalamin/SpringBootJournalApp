package com.hossainalamin.SpringBootProject.controller;

import com.hossainalamin.SpringBootProject.cache.JournalAppCache;
import com.hossainalamin.SpringBootProject.entity.Users;
import com.hossainalamin.SpringBootProject.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private UserService userService;
    @Autowired
    JournalAppCache journalAppCache;
    @GetMapping("all-user")
    public ResponseEntity<?> getAllUser() {
        List<Users> allUsers = userService.getAllUsers();
        if(allUsers != null && !allUsers.isEmpty()){
            return new ResponseEntity<>(allUsers, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @PostMapping("create-admin")
    public ResponseEntity<?> createAdmin(@RequestBody Users users){
        boolean users1 = userService.createAdminUsers(users);
        if(users1) {
            return new ResponseEntity<>(users1, HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
    @GetMapping
    public boolean clearCache(){
        if(journalAppCache.init()){
            return true;
        }
        return false;
    }
}
