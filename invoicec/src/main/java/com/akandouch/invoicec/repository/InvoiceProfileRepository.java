package com.akandouch.invoicec.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.akandouch.invoicec.domain.InvoiceProfile;

@Repository
public interface InvoiceProfileRepository extends MongoRepository<InvoiceProfile, String> {

}
