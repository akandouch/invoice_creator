package com.akandouch.invoicec.restcontroller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.akandouch.invoicec.domain.Invoice;
import com.akandouch.invoicec.pdf.InvoicePdf;
import com.akandouch.invoicec.service.InvoiceService;

@RestController
@RequestMapping("/invoice")
@CrossOrigin("*")
public class InvoiceRestController {
	

	@Autowired
	InvoiceService invoiceService;
	
	@GetMapping
	public List<Invoice> getInvoice() {
		return this.invoiceService.findAll();
	}
	
	@PostMapping(produces=MediaType.APPLICATION_JSON_VALUE, consumes=MediaType.APPLICATION_JSON_VALUE)
	public Invoice saveInvoice(@RequestBody Invoice invoice) {
		return this.invoiceService.save(invoice);
	}
	
	@DeleteMapping
	public void deleteInvoice(@RequestParam String id) {
		this.invoiceService.delete(id);
	}
	
	@GetMapping(value="generatepdf",produces=MediaType.APPLICATION_PDF_VALUE)
	public ResponseEntity<byte[]> generateInvoicePdf(String id) {
		Invoice invoice = this.invoiceService.findOne(id);
		byte[] f = InvoicePdf.generateInvoicePdf(invoice);
		
		ResponseEntity<byte[]> r = new ResponseEntity<byte[]>(f,HttpStatus.OK);
		return r;
	}

}
