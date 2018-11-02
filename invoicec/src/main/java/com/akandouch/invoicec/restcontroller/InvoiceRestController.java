package com.akandouch.invoicec.restcontroller;

import com.akandouch.invoicec.domain.Invoice;
import com.akandouch.invoicec.domain.InvoiceProfile;
import com.akandouch.invoicec.domain.Upload;
import com.akandouch.invoicec.pdf.InvoicePdf;
import com.akandouch.invoicec.service.InvoiceService;
import com.akandouch.invoicec.service.MailService;
import com.akandouch.invoicec.service.UploadService;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/invoice")
@CrossOrigin(value = "*", allowedHeaders = "*", exposedHeaders = "x-auth-token")
@Slf4j
public class InvoiceRestController extends CrudRestController<Invoice> {


    @Autowired
    private InvoiceService invoiceService;
    @Autowired
    private UploadService uploadService;
    @Autowired
    private MailService mailService;

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Invoice saveInvoice(@Valid @RequestBody Invoice invoice) {
        return this.invoiceService.save(invoice);
    }

    @GetMapping(value = "generatepdf", produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<byte[]> generateInvoicePdf(String id) {


        return new ResponseEntity<>(generatePdf(id), HttpStatus.OK);
    }

    @SneakyThrows
    private byte[] generatePdf(String id) {
        Invoice invoice = this.invoiceService.findOne(id);
        InvoiceProfile invoicer = invoice.getInvoicer();
        Upload logo = invoicer.getLogo();
        Invoice copy = invoice.toBuilder()
                .invoicer(invoicer.toBuilder().logo(logo != null ? uploadService.get(logo.getId()) : null).build())
                .build();
        return InvoicePdf.generateInvoicePdf(copy);
    }

    @SneakyThrows
    private File byteArrayToFile(Upload upload) {
        String fileName = upload.getFileName();
        File file = File.createTempFile(FilenameUtils.getBaseName(fileName), "." + FilenameUtils.getExtension(fileName));
        FileUtils.writeByteArrayToFile(file, upload.getUpload());
        return file;
    }

    @PostMapping(value = "send-mail/{id}")
    public Invoice sendInvoiceByMail(@PathVariable String id) {
        log.info(id);
        final Invoice invoice = this.invoiceService.findOne(id);
        InvoiceProfile invoicer = invoice.getInvoicer();
        InvoiceProfile invoiced = invoice.getInvoiced();
        log.info("send invoice by mail...");
        String subject = invoice.getInvoiceNumber() + ":" + invoice.getTitle();
        Supplier<List<File>> enrichFunction = () -> {
            List<File> attach =
                    invoice.getAttachments()
                            .stream()
                            .map(upload -> uploadService.get(upload.getId()))
                            .map(this::byteArrayToFile)
                            .collect(Collectors.toList());
            attach.add(byteArrayToFile(Upload.builder().upload(this.generatePdf(id)).fileName("invoice.pdf").build()));
            return attach;
        };
        mailService.sendMail(invoicer.getMail(),
                invoiced.getMail(),
                subject,
                "<b>Hey, here's your invoice !&copy;</b>",
                enrichFunction
        );
        log.info("mail will be sent asynchronously");
        return invoice;
    }

    @GetMapping(value = "create-new-invoice", produces = MediaType.APPLICATION_JSON_VALUE)
    public Invoice createNewInvoice() throws IOException {
        return invoiceService.createNewInvoice();
    }

}
