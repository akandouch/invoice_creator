package com.akandouch.invoicec.service;

import java.util.List;

import com.akandouch.invoicec.domain.InvoiceProfile;

public interface InvoiceProfileService {

	List<InvoiceProfile> findAll();
	InvoiceProfile findOne(String id);
	InvoiceProfile save(InvoiceProfile invoiceProfile);
	void delete(InvoiceProfile invoiceProfile);
}
