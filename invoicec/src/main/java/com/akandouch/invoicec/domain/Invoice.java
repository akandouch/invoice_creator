package com.akandouch.invoicec.domain;

import java.util.List;

public class Invoice {

	private Long id;
	private String title;
	private List<Item> items;
	
	private String cust_name;
	private String cust_street;
	private String cust_city;
	private String cust_postcode;
	private String cust_vat;
	
	private String name;
	private String street;
	private String city;
	private String postcode;
	private String vat;
	
	
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
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
	public String getCust_street() {
		return cust_street;
	}
	public void setCust_street(String cust_street) {
		this.cust_street = cust_street;
	}
	public String getCust_city() {
		return cust_city;
	}
	public void setCust_city(String cust_city) {
		this.cust_city = cust_city;
	}
	public String getCust_postcode() {
		return cust_postcode;
	}
	public void setCust_postcode(String cust_postcode) {
		this.cust_postcode = cust_postcode;
	}
	public String getCust_vat() {
		return cust_vat;
	}
	public void setCust_vat(String cust_vat) {
		this.cust_vat = cust_vat;
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
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
	public String getVat() {
		return vat;
	}
	public void setVat(String vat) {
		this.vat = vat;
	}
	
	
	
}
