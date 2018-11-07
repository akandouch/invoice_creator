package com.akandouch.invoicec.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class InvoiceProfile implements DomainEntity{
	
	private String id;

	@NotBlank
	@Size(min = 1, max = 255)
	private String firstname;

	@NotBlank
	@Size(min = 1, max = 255)
	private String lastname;

	private Boolean active;

	@NotBlank
	@Size(min = 1, max = 20)
	private String vat;

	@Email
	private String mail;

	@NotBlank
	private String phoneNumber;

	@Valid
	private Address address;

	@NotBlank
	private String accountNumber;

	private boolean customer;

	private Upload logo;

}
