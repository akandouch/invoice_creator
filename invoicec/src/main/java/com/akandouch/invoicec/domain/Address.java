package com.akandouch.invoicec.domain;

public class Address {

	private String street;
	private String streetNumber;
	private String city;
	private String postcode;
	private String country;

	private Address(Builder builder) {
		setStreet(builder.street);
		setStreetNumber(builder.streetNumber);
		setCity(builder.city);
		setPostcode(builder.postcode);
		setCountry(builder.country);
	}

	public static Builder newBuilder() {
		return new Builder();
	}

	public static Builder newBuilder(Address copy) {
		Builder builder = new Builder();
		builder.street = copy.getStreet();
		builder.streetNumber = copy.getStreetNumber();
		builder.city = copy.getCity();
		builder.postcode = copy.getPostcode();
		builder.country = copy.getCountry();
		return builder;
	}

	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public String getStreetNumber() {
		return streetNumber;
	}
	public void setStreetNumber(String streetNumber) {
		this.streetNumber = streetNumber;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getPostcode() {
		return postcode;
	}
	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	
	public Address(){}


	public static final class Builder {
		private String street;
		private String streetNumber;
		private String city;
		private String postcode;
		private String country;

		private Builder() {
		}

		public Builder street(String val) {
			street = val;
			return this;
		}

		public Builder streetNumber(String val) {
			streetNumber = val;
			return this;
		}

		public Builder city(String val) {
			city = val;
			return this;
		}

		public Builder postcode(String val) {
			postcode = val;
			return this;
		}

		public Builder country(String val) {
			country = val;
			return this;
		}

		public Address build() {
			return new Address(this);
		}
	}
}
