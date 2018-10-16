package com.akandouch.invoicec.domain;

public class InvoiceProfile {
	
	private String id;
	private String firstname;
	private String lastname;
	private Boolean active;
	
	private String vat;
	private Address address;

	private InvoiceProfile(Builder builder) {
		setId(builder.id);
		setFirstname(builder.firstname);
		setLastname(builder.lastname);
		setActive(builder.active);
		setVat(builder.vat);
		setAddress(builder.address);
	}

	public static Builder newBuilder() {
		return new Builder();
	}

	public static Builder newBuilder(InvoiceProfile copy) {
		Builder builder = new Builder();
		builder.id = copy.getId();
		builder.firstname = copy.getFirstname();
		builder.lastname = copy.getLastname();
		builder.active = copy.getActive();
		builder.vat = copy.getVat();
		builder.address = copy.getAddress();
		return builder;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public String getVat() {
		return vat;
	}

	public void setVat(String vat) {
		this.vat = vat;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public InvoiceProfile(){}

	public static final class Builder {
		private String id;
		private String firstname;
		private String lastname;
		private Boolean active;
		private String vat;
		private Address address;

		private Builder() {
		}

		public Builder id(String val) {
			id = val;
			return this;
		}

		public Builder firstname(String val) {
			firstname = val;
			return this;
		}

		public Builder lastname(String val) {
			lastname = val;
			return this;
		}

		public Builder active(Boolean val) {
			active = val;
			return this;
		}

		public Builder vat(String val) {
			vat = val;
			return this;
		}

		public Builder address(Address val) {
			address = val;
			return this;
		}

		public InvoiceProfile build() {
			return new InvoiceProfile(this);
		}
	}
}
