package com.hossainalamin.SpringBootProject.services;

import com.hossainalamin.SpringBootProject.entity.Users;
import com.hossainalamin.SpringBootProject.repository.UserRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

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

    public boolean updateUserById(ObjectId userId, Users user) {
        Optional<Users> userInfo = userRepository.findById(userId);
        if(userInfo != null){
            userRepository.save(user);
            return true;
        }else{
            return false;
        }
    }

    public Users findUserByUserName(String userName) {
        return userRepository.findByUserName(userName);
    }
}
