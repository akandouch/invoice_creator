package com.akandouch.invoicec.repository;

import com.akandouch.invoicec.domain.Item;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.akandouch.invoicec.domain.Invoice;

import java.util.List;

@Repository
public interface InvoiceRepository extends MongoRepository<Invoice, String>{
	List<Invoice> findInvoiceByItemsPeriodFromYearLessThanEqualAndItemsPeriodToYearGreaterThanEqual(int year, int year2);
	//void findAllByStatusIsGreaterThanAnd
}
