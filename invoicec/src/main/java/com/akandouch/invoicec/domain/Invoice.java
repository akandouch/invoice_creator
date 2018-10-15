package com.akandouch.invoicec.domain;

import java.util.List;

public class Invoice {
	
	private String id;
	private String title;
	private List<Item> items;
	
	private InvoiceProfile invoicer;
	private InvoiceProfile invoiced;

	public Invoice(){}

	private Invoice(Builder builder) {
		setId(builder.id);
		setTitle(builder.title);
		setItems(builder.items);
		setInvoicer(builder.invoicer);
		setInvoiced(builder.invoiced);
	}

	public static Builder newBuilder() {
		return new Builder();
	}

	public static Builder newBuilder(Invoice copy) {
		Builder builder = new Builder();
		builder.id = copy.getId();
		builder.title = copy.getTitle();
		builder.items = copy.getItems();
		builder.invoicer = copy.getInvoicer();
		builder.invoiced = copy.getInvoiced();
		return builder;
	}


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


	public static final class Builder {
		private String id;
		private String title;
		private List<Item> items;
		private InvoiceProfile invoicer;
		private InvoiceProfile invoiced;

		private Builder() {
		}

		public Builder id(String val) {
			id = val;
			return this;
		}

		public Builder title(String val) {
			title = val;
			return this;
		}

		public Builder items(List<Item> val) {
			items = val;
			return this;
		}

		public Builder invoicer(InvoiceProfile val) {
			invoicer = val;
			return this;
		}

		public Builder invoiced(InvoiceProfile val) {
			invoiced = val;
			return this;
		}

		public Invoice build() {
			return new Invoice(this);
		}
	}
}
