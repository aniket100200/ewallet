package com.example.majorproject;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

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
    public void sendEmail(String message) throws JsonProcessingException, MessagingException {
        JSONObject emailRequest = objectMapper.readValue(message, JSONObject.class);

        String toEmail = (String) emailRequest.get("email");
        String messageBody = (String) emailRequest.get("message");

        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");

        // Constructing a "Good Looking" HTML Body
        String htmlContent =
                "<div style='font-family: Arial, sans-serif; border: 1px solid #ddd; padding: 20px; border-radius: 10px; max-width: 600px;'>" +
                        "<h2 style='color: #2e6cbb;'>E-Wallet Transaction Update</h2>" +
                        "<hr style='border: 0; border-top: 1px solid #eee;' />" +
                        "<p style='font-size: 16px; color: #333;'>" + messageBody + "</p>" +
                        "<br/>" +
                        "<p style='font-size: 12px; color: #777;'>If you did not authorize this, please contact our support team immediately.</p>" +
                        "<footer style='margin-top: 20px; font-size: 10px; color: #aaa;'>" +
                        "© 2026 Avengers Bank Backend Team" +
                        "</footer>" +
                        "</div>";

        helper.setFrom(sender);
        helper.setTo(toEmail);
        helper.setSubject("Electronic Wallet: Transaction Alert");
        helper.setText(htmlContent, true); // 'true' enables HTML

        javaMailSender.send(mimeMessage);
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
