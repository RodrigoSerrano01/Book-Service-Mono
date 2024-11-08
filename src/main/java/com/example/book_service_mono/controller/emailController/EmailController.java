package com.example.book_service_mono.controller.emailController;

import com.example.book_service_mono.domain.emailDomain.Email;
import com.example.book_service_mono.service.emailService.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/book-service-mono/v1/")
public class EmailController {

    private final EmailService emailService;

    @PostMapping("/send-email")
    public String enviarEmail(@RequestBody Email email) {
        emailService.enviarEmail(email);
        return "E-mail enviado.";
    }
}