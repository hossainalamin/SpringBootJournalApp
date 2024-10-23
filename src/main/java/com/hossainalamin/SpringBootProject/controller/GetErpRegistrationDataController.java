package com.hossainalamin.SpringBootProject.controller;

import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/erp")
public class GetErpRegistrationDataController {
    @GetMapping
    public ResponseEntity<?> getErpRegistrationData(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return new ResponseEntity<>("Hi "+authentication.getName(), HttpStatus.OK);
    }
}
