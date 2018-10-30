package com.akandouch.invoicec.service;

import com.akandouch.invoicec.domain.Invoice;

import java.util.List;

public interface InvoiceService extends CrudService<Invoice> {

	Invoice save(Invoice invoice);
	Invoice findOne(String id);
	List<Invoice> findAll();
	Invoice createNewInvoice();
	void delete(String id);
}
