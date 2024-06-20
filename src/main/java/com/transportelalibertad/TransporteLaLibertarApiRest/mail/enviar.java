package com.transportelalibertad.TransporteLaLibertarApiRest.mail;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public class enviar {
    @Autowired
    private JavaMailSender javaMailSender;
    //@PostMapping("/enviar")
    /*
    public ResponseEntity<String> enviarMailConHtml(@RequestBody CorreoRequest correoRequest) {
        try {
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            helper.setTo(correoRequest.getDestinatario());
            helper.setFrom("tranporteLibertadApi@outlook.es");
            helper.setSubject(correoRequest.getAsunto());

            String htmlContent = correoRequest.getContenido() + "\n\n\n\nby Anthony_mss";

            helper.setText(htmlContent, true);
            javaMailSender.send(message);

            return ResponseEntity.ok("Correo enviado correctamente a " + correoRequest.getDestinatario());
        } catch (MessagingException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al enviar el correo");
        }


    }

     */
}
