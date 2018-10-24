package com.akandouch.invoicec.service;

import java.io.File;
import java.util.List;
import java.util.function.Function;

public interface MailService {
    void sendMail(String from, String to, String subject, String htmlBody, List<File> attachments, Function<List<File>, List<File>> enrichListOfFiles);
}
