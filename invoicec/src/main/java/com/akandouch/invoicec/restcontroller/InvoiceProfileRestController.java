package com.akandouch.invoicec.restcontroller;

import com.akandouch.invoicec.domain.InvoiceProfile;
import com.akandouch.invoicec.service.InvoiceProfileService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/invoiceprofile")
@CrossOrigin("*")
@Slf4j
public class InvoiceProfileRestController {

	@Autowired
	private  InvoiceProfileService invoiceProfileService;

	@GetMapping
	public List<InvoiceProfile> get() {
		return invoiceProfileService.findAll();
	}
	
	@PostMapping(consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	public InvoiceProfile set( @Valid @RequestBody InvoiceProfile invoiceProfile) {
		return invoiceProfileService.save(invoiceProfile);
	}
	
	@DeleteMapping
	public void delete(@RequestParam String id ) {
		System.out.println("rest controller : " + this.getClass().getName() + " : delete");
		InvoiceProfile ip = this.invoiceProfileService.findOne(id);
		this.invoiceProfileService.delete( ip );
	}
}
