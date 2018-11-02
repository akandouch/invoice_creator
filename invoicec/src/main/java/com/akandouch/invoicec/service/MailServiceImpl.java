package com.akandouch.invoicec.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.File;
import java.util.List;
import java.util.function.Supplier;

@Service
@Slf4j
public class MailServiceImpl implements MailService {


    private final JavaMailSender emailSender;

    @Autowired
    public MailServiceImpl(JavaMailSender emailSender) {
        this.emailSender = emailSender;
    }

    @Override
    @Async
    public void sendMail(String from, String to, String subject, String htmlBody, Supplier<List<File>> attachments) {
        try {
            final Multipart multipart = new MimeMultipart();
            final MimeMessage message = emailSender.createMimeMessage();

            final MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
            InternetAddress fromIA = new InternetAddress(from, from, "UTF-8");
            helper.setFrom(fromIA);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(htmlBody, true);
            helper.setBcc(fromIA);
            List<File> enrichAttachments = attachments.get();
            enrichAttachments.stream()
                    .forEach(a -> {
                        try {
                            helper.addAttachment(a.getName(), a);
                        } catch (MessagingException e) {
                            log.error("error with attachment: ", e);
                        }
                    });
            emailSender.send(helper.getMimeMessage());
            log.info("mail sent");
        } catch (Exception e) {
            log.error("an error occured ", e);
        }
    }

}
