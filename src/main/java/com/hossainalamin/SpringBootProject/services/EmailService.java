package com.hossainalamin.SpringBootProject.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
@Service
@Slf4j
public class EmailService {
    @Autowired
    private JavaMailSender javaMailSender;
    public String sendMail(String from, String to, String body){
        try {
            SimpleMailMessage mail = new SimpleMailMessage();
            mail.setFrom(from);
            mail.setTo(to);
            mail.setText(body);
            javaMailSender.send(mail);
            return "Main sent successfully";
        }catch (Exception ex){
            log.error("Execption occured "+ex);
        }
        return "Mail not sent";
    }
}
