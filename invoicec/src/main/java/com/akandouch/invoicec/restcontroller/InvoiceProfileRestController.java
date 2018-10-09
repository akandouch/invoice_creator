package com.akandouch.invoicec.restcontroller;

import java.lang.reflect.Method;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.akandouch.invoicec.domain.InvoiceProfile;
import com.akandouch.invoicec.service.InvoiceProfileService;

@RestController
@RequestMapping("/invoiceprofile")
@CrossOrigin("*")
public class InvoiceProfileRestController {

	@Autowired
	InvoiceProfileService invoiceProfileService;
	
	@GetMapping
	public List<InvoiceProfile> get() {
		System.out.println("rest controller : " + this.getClass().getName() + " : get all");
		return invoiceProfileService.findAll();
	}
	
	@PostMapping(consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	public InvoiceProfile set(@RequestBody InvoiceProfile invoiceProfile ) {
		return invoiceProfileService.save(invoiceProfile);
	}
	
	@DeleteMapping
	public void delete(@RequestParam String id ) {
		System.out.println("rest controller : " + this.getClass().getName() + " : delete");
		InvoiceProfile ip = this.invoiceProfileService.findOne(id);
		this.invoiceProfileService.delete( ip );
	}
}
