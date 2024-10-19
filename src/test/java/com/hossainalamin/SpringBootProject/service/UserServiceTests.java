package com.hossainalamin.SpringBootProject.service;

import com.hossainalamin.SpringBootProject.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class UserServiceTests {
    @Autowired
    private UserRepository userRepository;
    @Test
    public void findUserByName(){
        assertEquals(4,2+2);
        assertNotNull(userRepository.findByUserName("Arafa"));
    }
    @ParameterizedTest
    @CsvSource({
            "1,1,2",
            "2,2,4",
            "3,3,6"
    })
    public void testAdd(int a, int b, int expected){
        assertEquals(expected, a+b);
    }
}