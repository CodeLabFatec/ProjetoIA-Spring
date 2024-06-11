package br.gov.sp.fatec.projetoia.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailSender {

    @Autowired
    private JavaMailSender mailSender;

    private void sendEmail(String to, String subject, String body){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setText(body);
        message.setTo(to);
        message.setSubject(subject);
        message.setFrom("codelabfatec@outlook.com");

        mailSender.send(message);
    }

    public void sendEmailNewUser(String email, String password){
        String body = "Olá, para acessar nosso sistema utilize a senha: " + password;
        String subject = "Acesso ao ProjetoIA!";

        sendEmail(email, subject, body);
    }

    public void sendEmailRecoverToken(String email, String token){
        String body = "Olá, para recuperar sua senha em nosso sistema utilize o token abaixo: \n" + token;
        String subject = "Recuperar Acesso ao ProjetoIA!";

        sendEmail(email, subject, body);
    }
}