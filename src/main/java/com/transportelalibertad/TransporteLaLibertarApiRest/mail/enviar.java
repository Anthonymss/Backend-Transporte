package com.transportelalibertad.TransporteLaLibertarApiRest.mail;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;

@Component
public class enviar {

    @Autowired
    private JavaMailSender javaMailSender;

    @Async
    public void enviarmail(CorreoRequest correoRequest) {
        try {
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");


            helper.setTo(correoRequest.getDestinatario());
            helper.setFrom("liTraFast@outlook.com.pe");
            helper.setSubject(correoRequest.getAsunto());
            helper.setText(correoRequest.getContenido(), true);

            javaMailSender.send(message);
        } catch (Exception e) {
            System.err.println("Error sending \n"+e.getMessage());
        }
    }
}
