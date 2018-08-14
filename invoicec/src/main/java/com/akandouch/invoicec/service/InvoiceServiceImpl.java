package com.akandouch.invoicec.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.akandouch.invoicec.domain.Invoice;
import com.akandouch.invoicec.repository.InvoiceRepository;

@Service
public class InvoiceServiceImpl implements InvoiceService {

	@Autowired
	InvoiceRepository invoiceRepo;
	
	
	public Invoice save(Invoice invoice) {
		return this.invoiceRepo.save(invoice);
	}
	
	public Invoice findOne(Long id) {
		return this.invoiceRepo.findById(id).get();
	}
	
	public List<Invoice> findAll(){
		return this.invoiceRepo.findAll();
	}
}
