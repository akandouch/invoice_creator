package com.akandouch.invoicec.service;

import java.io.File;
import java.util.List;
import java.util.function.Supplier;

public interface MailService {
    void sendMail(String from, String to, String subject, String htmlBody, Supplier<List<File>> attachments);
}
