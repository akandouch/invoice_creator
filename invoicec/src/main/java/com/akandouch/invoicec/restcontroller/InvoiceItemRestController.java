package com.akandouch.invoicec.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.akandouch.invoicec.domain.Invoice;
import com.akandouch.invoicec.domain.Item;
import com.akandouch.invoicec.service.InvoiceService;
import com.akandouch.invoicec.service.ItemService;

@RestController
@RequestMapping("/invoice/{id}/item")
public class InvoiceItemRestController {
	
	

	@Autowired
	ItemService itemService;
	
	@Autowired
	InvoiceService invoiceService;
	
	@GetMapping
	public List<Item> findAll() {
		return this.itemService.findAll();
	}
	
	@PostMapping
	public Item save(@PathVariable String id, @RequestBody Item item) {
		System.out.println("-------- "  + id + " ---------");
		Invoice invoice = this.invoiceService.findOne(id);
		item.setAmount((double)item.getDays() * item.getRate());
		invoice.getItems().add(item);
		this.invoiceService.save(invoice);
		return item;//this.itemService.save(item);
	}
}
