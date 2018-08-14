package com.akandouch.invoicec.restcontroller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.akandouch.invoicec.domain.Invoice;
import com.akandouch.invoicec.service.InvoiceService;

@RestController
@RequestMapping("/invoice")
@CrossOrigin("*")
public class InvoiceRestController {
	

	@Autowired
	InvoiceService invoiceService;
	
	@GetMapping
	public List<Invoice> getInvoice() {
		return this.invoiceService.findAll();
	}
	
	@PostMapping(produces=MediaType.APPLICATION_JSON_VALUE, consumes=MediaType.APPLICATION_JSON_VALUE)
	public Invoice saveInvoice(@RequestBody Invoice invoice) {
		/*Invoice i = new Invoice();
		i.setId(777L);
		List<Item> items = new ArrayList<Item>();
		Item test = new Item();
		test.setDescription("just for test");
		test.setAmount(25.75);
		test.setUnit(8);
		
		items.add(test);items.add(test);items.add(test);items.add(test);items.add(test);items.add(test);
		
		i.setItems(items);*/
		
		return this.invoiceService.save(invoice);
		
	}

}
