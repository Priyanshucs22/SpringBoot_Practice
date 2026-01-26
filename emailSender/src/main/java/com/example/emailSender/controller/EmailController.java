package com.example.emailSender.controller;

import com.example.emailSender.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/email")
public class EmailController {

    @Autowired
    private EmailService emailService;

    @PostMapping("/send/{to}/{subject}/{body}")
    public String sendEmail(
            @PathVariable String to,
            @PathVariable String subject,
            @PathVariable String body) {

        emailService.sendEmail(to, subject, body);
        return "Email sent successfully!";
    }

}
