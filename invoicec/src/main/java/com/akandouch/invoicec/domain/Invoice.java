package com.akandouch.invoicec.domain;

import java.util.List;

public class Invoice {
	
	private String id;
	private String title;
	private List<Item> items;
	
	private InvoiceProfile invoicer;
	private InvoiceProfile invoiced;
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public List<Item> getItems() {
		return items;
	}
	public void setItems(List<Item> items) {
		this.items = items;
	}
	public InvoiceProfile getInvoicer() {
		return invoicer;
	}
	public void setInvoicer(InvoiceProfile invoicer) {
		this.invoicer = invoicer;
	}
	public InvoiceProfile getInvoiced() {
		return invoiced;
	}
	public void setInvoiced(InvoiceProfile invoiced) {
		this.invoiced = invoiced;
	}
	
	
	
}
