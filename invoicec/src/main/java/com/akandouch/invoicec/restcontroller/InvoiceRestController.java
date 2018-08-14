package com.akandouch.invoicec.restcontroller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.akandouch.invoicec.domain.Invoice;
import com.akandouch.invoicec.domain.Item;
import com.akandouch.invoicec.service.InvoiceService;

@RestController
@RequestMapping("/invoice")
public class InvoiceRestController {
	

	@Autowired
	InvoiceService invoiceService;
	
	@GetMapping
	public Invoice getInvoice(@RequestParam Long id) {
		return new Invoice();
	}
	
	@PostMapping(produces=MediaType.APPLICATION_JSON_VALUE)
	public Invoice saveInvoice() {
		Invoice i = new Invoice();
		i.setId(777L);
		List<Item> items = new ArrayList<Item>();
		Item test = new Item();
		test.setDescription("just for test");
		test.setAmount(25.75);
		test.setUnit(8);
		
		items.add(test);items.add(test);items.add(test);items.add(test);items.add(test);items.add(test);
		
		i.setItems(items);
		
		return this.invoiceService.save(i);
		
	}

}
