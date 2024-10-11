package com.hossainalamin.SpringBootProject.services;

import com.hossainalamin.SpringBootProject.entity.Users;
import com.hossainalamin.SpringBootProject.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserService{
    @Autowired
    UserRepository userRepository;
    public boolean createUsers(Users user){
        userRepository.save(user);
        return true;
    }
    public List<Users> getAllUsers(){
        return userRepository.findAll();
    }

}
