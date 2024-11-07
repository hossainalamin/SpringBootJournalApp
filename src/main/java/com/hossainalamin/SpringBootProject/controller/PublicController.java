package com.hossainalamin.SpringBootProject.controller;

import com.hossainalamin.SpringBootProject.entity.Users;
import com.hossainalamin.SpringBootProject.services.UserDetailsServiceImp;
import com.hossainalamin.SpringBootProject.services.UserService;
import com.hossainalamin.SpringBootProject.utils.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
@Slf4j
@RestController
@RequestMapping("/public")
public class PublicController {
    @Autowired
    private UserService userService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserDetailsServiceImp userDetailsServiceImp;

    @Autowired
    private JwtUtil jwtUtil;
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


    @PostMapping("/sign-up")
    public ResponseEntity<?> createAdmin(@RequestBody Users users){
        boolean users1 = userService.createAdminUsers(users);
        if(users1) {
            return new ResponseEntity<>(users1, HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
    @PostMapping("/login")
    public ResponseEntity<?> loginAdmin(@RequestBody Users users){
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(users.getUserName(), users.getPassword()));
            UserDetails userDetails = userDetailsServiceImp.loadUserByUsername(users.getUserName());
            jwtUtil.generateToken(userDetails.getUsername());
        }catch (Exception ex){
            log.error("Wrong password "+ex.getMessage());
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
