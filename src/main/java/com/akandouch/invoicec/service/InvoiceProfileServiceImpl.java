package com.akandouch.invoicec.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
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
	public Page<InvoiceProfile> findAllByPage(Integer pageSize, Integer pageNumber, String orderColumn, String direction) {
		if(orderColumn != null ){
			Sort s = Sort.by(orderColumn).descending();
			if(direction.equals("asc")){
				s = Sort.by(orderColumn).ascending();
			}
			return this.invoiceProfileRepo.findAll(PageRequest.of(pageNumber,pageSize, s));
		}
		return this.invoiceProfileRepo.findAll(PageRequest.of(pageNumber,pageSize));
	}

	@Override
	public void delete(String id) {
		this.invoiceProfileRepo.deleteById(id);
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
