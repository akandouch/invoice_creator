package com.akandouch.invoicec.service;

import java.io.File;
import java.util.List;

public interface MailService {
    void sendMail(String from, String to, String subject, String htmlBody, List<File> attachments);
}
