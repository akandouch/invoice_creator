package com.akandouch.invoicec.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.akandouch.invoicec.domain.InvoiceProfile;
import com.akandouch.invoicec.repository.InvoiceProfileRepository;

@Service
public class InvoiceProfileServiceImpl implements InvoiceProfileService {

	@Autowired
	InvoiceProfileRepository invoiceProfileRepo;
	
	@Override
	public List<InvoiceProfile> findAll() {
		return invoiceProfileRepo.findAll();
	}

	@Override
	public InvoiceProfile findOne(String id) {
		return invoiceProfileRepo.findById(id).orElseGet(null);
	}

	@Override
	public InvoiceProfile save(InvoiceProfile invoiceProfile) {
		return this.invoiceProfileRepo.save(invoiceProfile);
	}
	
	@Override
	public void delete(InvoiceProfile invoiceProfile) {
		this.invoiceProfileRepo.delete(invoiceProfile);
	}

}
