package com.akandouch.invoicec.service;

import java.util.List;

import com.akandouch.invoicec.domain.Invoice;

public interface InvoiceService {

	Invoice save(Invoice invoice);
	Invoice findOne(Long id);
	List<Invoice> findAll();
}
