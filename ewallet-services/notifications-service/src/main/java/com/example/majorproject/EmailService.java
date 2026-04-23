package com.example.majorproject;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    JavaMailSender javaMailSender;

    @Autowired
    SimpleMailMessage simpleMailMessage;

    @Value("${spring.mail.username}")
    String sender;

    @KafkaListener(topics={"send_email"},groupId = "avengers")
    public void sendEmail(String message) throws JsonProcessingException {


        JSONObject emailRequest= objectMapper.readValue(message, JSONObject.class);

        String toEmail = (String) emailRequest.get("email");
        String messageBody = (String) emailRequest.get("message");

        simpleMailMessage.setFrom(sender);
        simpleMailMessage.setTo(toEmail);
        simpleMailMessage.setSubject("Transaction Email");
        simpleMailMessage.setText(messageBody);

        javaMailSender.send(simpleMailMessage);
    }

    public void sendEmailToEmailId(String toEmail){
        String messageBody= "Hello "+toEmail+" Welcome to the EmailService";
        simpleMailMessage.setFrom(sender);
        simpleMailMessage.setTo(toEmail);
        simpleMailMessage.setSubject("Transaction Email");
        simpleMailMessage.setText(messageBody);
        javaMailSender.send(simpleMailMessage);

    }
}
