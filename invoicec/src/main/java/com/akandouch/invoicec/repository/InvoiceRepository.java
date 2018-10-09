package com.akandouch.invoicec.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.akandouch.invoicec.domain.Invoice;

@Repository
public interface InvoiceRepository extends MongoRepository<Invoice, String>{
	
}
