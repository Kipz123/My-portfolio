package com.allankaino.portfolio.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class ContactController {

    @Autowired
    private com.allankaino.portfolio.service.EmailService emailService;

    @org.springframework.beans.factory.annotation.Value("${spring.mail.username}")
    private String myEmail;

    @PostMapping("/contact")
    public String handleContact(@RequestParam Map<String, String> body) {
        String subject = "New Contact Request from " + body.getOrDefault("name", "Unknown");
        StringBuilder messageBody = new StringBuilder();
        messageBody.append("Name: ").append(body.get("name")).append("\n");
        messageBody.append("Email: ").append(body.get("email")).append("\n");
        messageBody.append("Message: ").append(body.get("message")).append("\n");
        
        // Log the contact request
        System.out.println("New Contact Request: " + body);

        // Send email
        emailService.sendEmail(myEmail, subject, messageBody.toString());

        return "Message received! We will get back to you soon.";
    }
}
