package com.hossainalamin.SpringBootProject.services;

import com.hossainalamin.SpringBootProject.entity.Users;
import com.hossainalamin.SpringBootProject.repository.UserRepository;
import org.apache.catalina.User;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Component
public class UserService{
    @Autowired
    private UserRepository userRepository;
    private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    public boolean createUsers(Users user){
        userRepository.save(user);
        return true;
    }
    public boolean createNewUser(Users users){
        users.setPassword(passwordEncoder.encode(users.getPassword()));
        users.setRoles(Arrays.asList("Users"));
        userRepository.save(users);
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

    public boolean deleteUserByName(String name) {
        userRepository.deleteUserByUserName(name);
        return true;

    }
}
