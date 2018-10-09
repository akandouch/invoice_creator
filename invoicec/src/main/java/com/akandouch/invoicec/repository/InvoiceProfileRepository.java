package com.akandouch.invoicec.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.akandouch.invoicec.domain.InvoiceProfile;

public interface InvoiceProfileRepository extends MongoRepository<InvoiceProfile, String> {

}
