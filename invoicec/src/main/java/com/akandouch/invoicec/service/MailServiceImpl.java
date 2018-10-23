package com.akandouch.invoicec.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.File;
import java.util.List;

@Service
@Slf4j
public class MailServiceImpl implements MailService {


    private final JavaMailSender emailSender;

    @Autowired
    public MailServiceImpl(JavaMailSender emailSender) {
        this.emailSender = emailSender;
    }


    @Async
    public void sendMail(String from, String to, String subject, String htmlBody, List<File> attachments) {
        try {
            final Multipart multipart = new MimeMultipart();
            final MimeMessage message = emailSender.createMimeMessage();
            message.setFrom(from);
            message.setRecipients(Message.RecipientType.TO, to);
            message.setSubject(subject);
            for (File f : attachments) {
                BodyPart attachmentBodyPart = new MimeBodyPart();
                attachmentBodyPart.setDataHandler(new DataHandler(new FileDataSource(f)));
                attachmentBodyPart.setFileName(f.getName());
                multipart.addBodyPart(attachmentBodyPart);
            }
            BodyPart htmlBodyPart = new MimeBodyPart();
            htmlBodyPart.setContent(htmlBody, "text/html; charset=UTF-8");
            multipart.addBodyPart(htmlBodyPart);
            message.setContent(multipart);
            emailSender.send(message);
            log.info("mail sent");
        } catch (Exception e) {
            log.error("an error occured ", e);
        }
    }

}
