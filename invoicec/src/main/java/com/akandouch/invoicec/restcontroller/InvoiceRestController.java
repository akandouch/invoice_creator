package com.akandouch.invoicec.restcontroller;

import com.akandouch.invoicec.domain.Invoice;
import com.akandouch.invoicec.pdf.InvoicePdf;
import com.akandouch.invoicec.service.InvoiceService;
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
        byte[] f = InvoicePdf.generateInvoicePdf(invoice);

        ResponseEntity<byte[]> r = new ResponseEntity<>(f, HttpStatus.OK);
        return r;
    }

}
