package com.example.book_service_mono.service.emailService;

import com.example.book_service_mono.domain.emailDomain.Email;
import com.example.book_service_mono.exception.EmailException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class EmailService {

    private final JavaMailSender mailSender;

    public void enviarEmail(Email email) {
        try {
            SimpleMailMessage mensagem = new SimpleMailMessage();
            System.out.println(email.getTo());
            mensagem.setTo(email.getTo());
            mensagem.setSubject(email.getSubject());
            mensagem.setText(email.getBody());
            mensagem.setFrom("book-gate.com");
            log.info("Enviando e-mail para " + email.getTo());

            mailSender.send(mensagem);
        } catch (Exception e) {
            log.error("O e-mail não pôde ser enviado por conta da exception: " + e);
            throw new EmailException("Erro ao enviar e-mail para " + email.getTo());
        }
    }
}
