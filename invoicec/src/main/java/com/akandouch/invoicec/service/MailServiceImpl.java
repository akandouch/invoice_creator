package com.akandouch.invoicec.service;

import freemarker.template.Configuration;
import freemarker.template.Template;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;
import java.util.stream.Collectors;

@Service
@Slf4j
public class MailServiceImpl implements MailService {


    private final JavaMailSender emailSender;
    private final Configuration configuration;

    @Autowired
    public MailServiceImpl(JavaMailSender emailSender, Configuration configuration) {
        this.emailSender = emailSender;
        this.configuration = configuration;
    }

    @Override
    @Async
    public void sendMail(String from, String to, String subject, String htmlBody, Supplier<List<File>> attachments) {
        try {
            final MimeMessage message = emailSender.createMimeMessage();

            final MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
            InternetAddress fromIA = new InternetAddress(from, from, "UTF-8");
            helper.setFrom(fromIA);
            helper.setTo(to);
            helper.setSubject(subject);
            List<File> enrichAttachments = attachments.get();
            enrichAttachments.stream()
                    .forEach(a -> {
                        try {
                            helper.addAttachment(a.getName(), a);
                        } catch (MessagingException e) {
                            log.error("error with attachment: ", e);
                        }
                    });
            Map<String, Object> data = new HashMap<>();
            data.put("subject", subject);
            data.put("htmlBody", htmlBody);
            data.put("attachments", enrichAttachments.stream().map(File::getName).collect(Collectors.toList()));

            Template t = configuration.getTemplate("email-template.ftl");

            String readyParsedTemplate = FreeMarkerTemplateUtils.processTemplateIntoString(t, data);


            helper.setText(readyParsedTemplate, true);
            helper.setBcc(fromIA);

            emailSender.send(helper.getMimeMessage());
            log.info("mail sent");
        } catch (Exception e) {
            log.error("an error occured ", e);
        }
    }

}
