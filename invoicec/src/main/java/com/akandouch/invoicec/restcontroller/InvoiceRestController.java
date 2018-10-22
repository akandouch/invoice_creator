package com.akandouch.invoicec.restcontroller;

import com.akandouch.invoicec.domain.Invoice;
import com.akandouch.invoicec.domain.InvoiceProfile;
import com.akandouch.invoicec.domain.Upload;
import com.akandouch.invoicec.pdf.InvoicePdf;
import com.akandouch.invoicec.service.InvoiceService;
import com.akandouch.invoicec.service.UploadService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/invoice")
@CrossOrigin("*")
@Slf4j
public class InvoiceRestController {


    @Autowired
    private InvoiceService invoiceService;
    @Autowired
    private UploadService uploadService;

    @GetMapping
    public List<Invoice> getInvoice() {
        return this.invoiceService.findAll();
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Invoice saveInvoice(@Valid @RequestBody Invoice invoice) {
        return this.invoiceService.save(invoice);
    }

    @DeleteMapping
    public void deleteInvoice(@RequestParam String id) {
        this.invoiceService.delete(id);
    }

    @GetMapping(value = "generatepdf", produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<byte[]> generateInvoicePdf(String id) throws IOException {
        Invoice invoice = this.invoiceService.findOne(id);
        InvoiceProfile invoicer = invoice.getInvoicer();
        Upload logo = invoicer.getLogo();
        Invoice copy = invoice.toBuilder()
                .invoicer(invoicer.toBuilder().logo(logo != null ? uploadService.get(logo.getId()) : null).build())
                .build();
        byte[] f = InvoicePdf.generateInvoicePdf(copy);

        return new ResponseEntity<>(f, HttpStatus.OK);
    }

    @GetMapping(value = "create-new-invoice", produces = MediaType.APPLICATION_JSON_VALUE)
    public Invoice createNewInvoice() throws IOException {
        return invoiceService.createNewInvoice();
    }

}
